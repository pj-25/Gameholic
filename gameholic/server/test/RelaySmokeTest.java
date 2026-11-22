import gameholic.server.services.GameControlEvent;
import gameholic.server.services.PlayerConnection;
import jsc.jConnection.JConnection;
import jsc.jMessageHandler.JMessageFormatHandler;
import jsc.jServer.JServer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/** Two real clients against a real GameholicServer relay: create -> join -> START_GAME both sides -> PASS_DATA relayed. */
public class RelaySmokeTest {
    static void check(boolean ok, String what) {
        if (!ok) { System.err.println("FAIL: " + what); System.exit(1); }
        System.out.println("ok   " + what);
    }
    static String code(GameControlEvent e) { return String.valueOf(e.getCode()); }

    public static void main(String[] args) throws Exception {
        JServer server = new JServer(0, PlayerConnection.class);
        Thread t = new Thread(server::start); t.setDaemon(true); t.start();
        int port = server.getServerSocket().getLocalPort();

        BlockingQueue<String> creatorIn = new LinkedBlockingQueue<>(), opponentIn = new LinkedBlockingQueue<>();
        JConnection creator = new JConnection("127.0.0.1", port, creatorIn::add); creator.run();
        JConnection opponent = new JConnection("127.0.0.1", port, opponentIn::add); opponent.run();

        creator.write(JMessageFormatHandler.encode(GameControlEvent.CREATE_GSESSION, "tictactoe", "alice"));
        String created = creatorIn.poll(5, TimeUnit.SECONDS);
        check(created != null && created.startsWith(code(GameControlEvent.CREATE_GSESSION) + ">"), "creator receives CREATE_GSESSION with id: " + created);
        String sessionId = created.substring(created.indexOf('>') + 1);

        opponent.write(JMessageFormatHandler.encode(GameControlEvent.JOIN_GSESSION, sessionId, "bob"));
        String startC = creatorIn.poll(5, TimeUnit.SECONDS), startO = opponentIn.poll(5, TimeUnit.SECONDS);
        check(startC != null && startC.startsWith(code(GameControlEvent.START_GAME)) && startC.endsWith("bob"), "creator gets START_GAME vs bob: " + startC);
        check(startO != null && startO.startsWith(code(GameControlEvent.START_GAME)) && startO.endsWith("alice"), "opponent gets START_GAME vs alice: " + startO);

        opponent.write(JMessageFormatHandler.encode(GameControlEvent.PASS_DATA, "move:4"));
        check("move:4".equals(creatorIn.poll(5, TimeUnit.SECONDS)), "PASS_DATA relayed opponent -> creator");
        creator.write(JMessageFormatHandler.encode(GameControlEvent.PASS_DATA, "move:0"));
        check("move:0".equals(opponentIn.poll(5, TimeUnit.SECONDS)), "PASS_DATA relayed creator -> opponent");

        opponent.write(JMessageFormatHandler.encode(GameControlEvent.JOIN_GSESSION, "no-such-id", "eve"));
        check(code(GameControlEvent.INVALID_SESSION_ID).concat(">").equals(opponentIn.poll(5, TimeUnit.SECONDS)), "unknown session id -> INVALID_SESSION_ID");
        System.out.println("PASS relay smoke test");
        System.exit(0);
    }
}
