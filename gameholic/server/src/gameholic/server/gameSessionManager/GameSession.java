package gameholic.server.gameSessionHandler;

import gameholic.server.services.GameControlEvent;
import gameholic.server.services.PlayerConnection;
import jsc.jMessageHandler.JMessageFormatHandler;


public class GameSession {

    private String gameSessionID;
    private String gameName;
    private PlayerConnection[] playerConnections;
    private String []playerNames;
    private boolean isRunning = false;

    public GameSession(String gameName, String creatorName, PlayerConnection creatorConnection) {
        playerNames = new String[2];
        playerConnections = new PlayerConnection[2];
        setGameName(gameName);
        setCreator(creatorName, creatorConnection);
        setGameSessionID(createNewID());
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public GameSession(String gameName, String creatorName, PlayerConnection creatorConnection, String opponentName, PlayerConnection opponentConnection){
        this(gameName,creatorName, creatorConnection);
        joinOpponent(opponentName, opponentConnection);
    }

    private void setCreator(String creatorName, PlayerConnection creatorConnection) {
        setCreatorName(creatorName);
        setCreatorConnection(creatorConnection);
        creatorConnection.setConnectionID(creatorName);
    }

    private void setOpponent(String opponentName, PlayerConnection opponentConnection){
        setOpponentName(opponentName);
        setOpponentConnection(opponentConnection);
        opponentConnection.setConnectionID(opponentName);
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
        return createNewID( getGameName(),getCreatorName(), this);
    }

    public static String createNewID(String gameName, String creatorName, GameSession gameSession){
        return gameName +"_" + creatorName + "_" + gameSession.hashCode();
    }

    public PlayerConnection[] getPlayerConnections() {
        return playerConnections;
    }

    public void setPlayerConnection(PlayerType playerType, PlayerConnection playerConnection){
        this.playerConnections[playerType.getCode()] = playerConnection;
    }

    public PlayerConnection getPlayerConnection(PlayerType playerType){
        return playerConnections[playerType.getCode()];
    }

    public void setPlayerConnections(PlayerConnection[] playerConnections) {
        this.playerConnections = playerConnections;
    }

    public PlayerConnection getCreatorConnection(){
        return getPlayerConnection(PlayerType.CREATOR);
    }

    public PlayerConnection getOpponentConnection(){
        return getPlayerConnection(PlayerType.OPPONENT);
    }

    public void setCreatorConnection(PlayerConnection creatorConnection){
        setPlayerConnection(PlayerType.CREATOR, creatorConnection);
    }

    public void setOpponentConnection(PlayerConnection opponentConnection){
        setPlayerConnection(PlayerType.OPPONENT, opponentConnection);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void joinOpponent(String opponentName, PlayerConnection opponentConnection){
        setOpponent(opponentName, opponentConnection);
        try{
            start();
        }catch (OpponentNotJoinedException opponentNotJoinedException){
            opponentNotJoinedException.printStackTrace();
        }
    }

    public boolean hasOpponentJoined(){
        return getOpponentConnection()!=null;
    }

    public void start() throws OpponentNotJoinedException{
        if(!hasOpponentJoined()){
            throw new OpponentNotJoinedException("Opponent must join to start the game");
        }
        isRunning = true;
        send(PlayerType.CREATOR, JMessageFormatHandler.encode(GameControlEvent.START_GAME, gameName, getOpponentName()));
        send(PlayerType.OPPONENT, JMessageFormatHandler.encode(GameControlEvent.START_GAME, gameName, getCreatorName()));
    }

    public void send(PlayerType playerType, String msg){
        getPlayerConnection(playerType).send(msg);
    }

    public void end(GameControlEvent endEvent){
        send(PlayerType.CREATOR, JMessageFormatHandler.encode(endEvent));
        if(hasOpponentJoined()){
            send(PlayerType.OPPONENT, JMessageFormatHandler.encode(endEvent));
        }
        setRunning(false);
    }

    public static class OpponentNotJoinedException extends Exception{
        public OpponentNotJoinedException(String errorMsg){
            super(errorMsg);
        }
    }
}
