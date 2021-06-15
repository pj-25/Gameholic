package gameholic.server.gameSessionHandler;

import gameholic.server.services.GameEvent;
import gameholic.server.services.ServeRequest;
import jsc.jMessageHandler.JMessageFormatHandler;


public class GameSession {

    private String gameSessionID;
    private ServeRequest[] playerConnections;
    private String []playerNames;
    private boolean isRunning = false;


    public GameSession(String creatorName, ServeRequest creatorConnection) {
        playerNames = new String[2];
        playerConnections = new ServeRequest[2];
        setCreator(creatorName, creatorConnection);
        setGameSessionID(createNewID());
    }

    public GameSession(String creatorName, ServeRequest creatorConnection, String opponentName, ServeRequest opponentConnection){
        this(creatorName, creatorConnection);
        joinOpponent(opponentName, opponentConnection);
    }

    private void setCreator(String creatorName, ServeRequest creatorConnection) {
        setCreatorName(creatorName);
        setCreatorConnection(creatorConnection);
    }

    public String getGameSessionID() {
        return gameSessionID;
    }

    public void setGameSessionID(String gameSessionID) {
        this.gameSessionID = gameSessionID;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String creator, String opponent){
        setCreatorName(creator);
        setOpponentName(opponent);
    }

    public void setPlayerNames(String[] playerNames){
        this.playerNames = playerNames;
    }

    public void setOpponentName(String opponentName){
        this.playerNames[1] = opponentName;
    }

    public void setCreatorName(String creatorName){
        this.playerNames[0] = creatorName;
    }

    public String getCreatorName(){
        return playerNames[0];
    }

    public String getOpponentName(){
        return playerNames[1];
    }

    public String createNewID(){
        return createNewID(getCreatorName(), this);
    }

    public static String createNewID(String creatorName, GameSession gameSession){
        return creatorName + "_" + gameSession.hashCode();
    }

    public ServeRequest[] getPlayerConnections() {
        return playerConnections;
    }

    public void setPlayerConnections(ServeRequest[] playerConnections) {
        this.playerConnections = playerConnections;
    }

    public ServeRequest getPlayerConnection(PlayerType playerType){
        return playerConnections[playerType.getCode()];
    }

    public ServeRequest getCreatorConnection(){
        return playerConnections[0];
    }

    public ServeRequest getOpponentConnection(){
        return playerConnections[1];
    }

    public void setCreatorConnection(ServeRequest creatorConnection){
        this.playerConnections[0] = creatorConnection;
    }

    public void setOpponentConnection(ServeRequest opponentConnection){
        this.playerConnections[1] = opponentConnection;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void joinOpponent(String opponentName, ServeRequest opponentConnection){
        setOpponentName(opponentName);
        setOpponentConnection(opponentConnection);
        try{
            start();
        }catch (OpponentNotJoinedException ignore){}
    }

    public boolean hasOpponentJoined(){
        return getOpponentConnection()!=null;
    }

    public void start() throws OpponentNotJoinedException{
        if(!hasOpponentJoined()){
            throw new OpponentNotJoinedException("Opponent must join to start the game");
        }
        isRunning = true;
        send(PlayerType.CREATOR, JMessageFormatHandler.encode(GameEvent.START_GAME, getOpponentName()));
        send(PlayerType.OPPONENT, JMessageFormatHandler.encode(GameEvent.START_GAME, getCreatorName()));
    }

    public void send(PlayerType playerType, String msg){
        getPlayerConnection(playerType).send(msg);
    }

    public static class OpponentNotJoinedException extends Exception{
        public OpponentNotJoinedException(String errorMsg){
            super(errorMsg);
        }
    }

    public void end(GameEvent endEvent){
        send(PlayerType.CREATOR, JMessageFormatHandler.encode(endEvent));
        if(hasOpponentJoined()){
            send(PlayerType.OPPONENT, JMessageFormatHandler.encode(endEvent));
        }
        setRunning(false);
    }
}
