package gameholic.server.services;

import gameholic.server.gameSessionHandler.GameSession;
import gameholic.server.gameSessionHandler.PlayerType;
import jsc.jMessageHandler.JMessageDelimiter;
import jsc.jMessageHandler.JMessageFormatHandler;
import jsc.jServer.JRequestManager;

import java.util.HashMap;

public class PlayerConnection extends JRequestManager {
    public static HashMap<String, GameSession> gameSessionMap = new HashMap<>();
    private GameSession gameSession;
    private PlayerType playerType;

    @Override
    public void accept(String data) {
        String[] gData = JMessageFormatHandler.decode(JMessageDelimiter.EVENT_TYPE_DELIMITER, data,2);
        GameEvent gameEvent = GameEvent.get(Integer.parseInt(gData[0]));
        gData = JMessageFormatHandler.decode(gData[1]);
        switch (gameEvent) {
            case CREATE_GSESSION:
                createGSession(gData[0], gData[1]);
                break;

            case JOIN_GSESSION:
                joinGSession(gData[0], gData[1]);
                break;

            case PASS_DATA:
                passData(gData[0]);
                break;

            case END_GSESSION:
                endGSession();
                break;

            case GAME_OVER:
                gameOver();
                break;

            default:
                System.out.println("Invalid request");
                break;
        }
    }

    public void createGSession(String gameName, String creatorName){
        GameSession newGameSession = new GameSession(gameName,creatorName, this);
        gameSessionMap.put(newGameSession.getGameSessionID(), newGameSession);
        setPlayer(PlayerType.CREATOR, newGameSession);
        send(JMessageFormatHandler.encode(GameEvent.CREATE_GSESSION, newGameSession.getGameSessionID()));
    }

    public void joinGSession(String joinSessionId, String opponentName){
        if(!gameSessionMap.containsKey(joinSessionId)){
            send(JMessageFormatHandler.encode(GameEvent.INVALID_SESSION_ID));
        }else{
            setPlayer(PlayerType.OPPONENT, gameSessionMap.get(joinSessionId));
            gameSession.joinOpponent(opponentName, this);
        }
    }

    public void passData(String data){
        gameSession.send(getCompetitorType(), data);
    }

    public PlayerType getCompetitorType(){
        return (playerType == PlayerType.CREATOR)?PlayerType.OPPONENT:PlayerType.CREATOR;
    }

    public void endGSession(GameEvent endEvent){
        gameSession.end(endEvent);
    }

    public void endGSession(){
        endGSession(GameEvent.END_GSESSION);
    }

    public void gameOver(){
        endGSession(GameEvent.GAME_OVER);
    }

    public static HashMap<String, GameSession> getGameSessionMap() {
        return gameSessionMap;
    }

    public static void setGameSessionMap(HashMap<String, GameSession> gameSessionMap) {
        PlayerConnection.gameSessionMap = gameSessionMap;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setPlayer(PlayerType playerType, GameSession gameSession){
        setPlayerType(playerType);
        setGameSession(gameSession);
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    @Override
    public void close(){
        super.close();
        if(getGameSession().isRunning())
            gameSessionMap.remove(getGameSession().getGameSessionID());
    }
}
