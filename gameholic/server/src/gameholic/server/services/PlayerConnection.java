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
        String []reqData = JMessageFormatHandler.decode(JMessageDelimiter.REQUEST_TYPE_DELIMITER, data, 2);

        String[] gData = JMessageFormatHandler.decode(JMessageDelimiter.EVENT_TYPE_DELIMITER, reqData[1]);
        GameEvent gameEvent = GameEvent.get(Integer.parseInt(gData[0]));

        switch (gameEvent) {
            case CREATE_GSESSION:
                createGSession(gData[1], gData[2]);
                break;

            case JOIN_GSESSION:
                joinGSession(gData[1], gData[2]);
                break;

            case PASS_DATA:
                passData(gData[1]);
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
    }

    public void joinGSession(String joinSessionId, String opponentName){
        if(!gameSessionMap.containsKey(joinSessionId)){
            send(JMessageFormatHandler.encode(GameEvent.INVALID_SESSION_ID+""));
        }else{
            gameSession.joinOpponent(opponentName, this);
            setPlayer(PlayerType.OPPONENT, gameSessionMap.get(joinSessionId));
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
    public void onClose(){
        gameSessionMap.remove(getGameSession().getGameSessionID());
        super.onClose();
    }
}
