package gameholic.gameManager;

import jsc.jConnection.JConnectionManager;
import jsc.jEventManager.JEventCode;
import jsc.jEventManager.JEventConsumer;
import jsc.jEventManager.JEventManager;
import jsc.jEventManager.JEventType;

import java.io.IOException;
import java.util.Properties;

public class GameManager {
    private static JConnectionManager jConnectionManager;
    private static final JEventManager jEventManager;
    private static String[] playerNames;
    private static String gameSessionID;
    private static String gameName;
    private static GameMode gameMode;

    private static Properties gameConfig;
    public static final String GAME_CONFIG_FILE = "/gameholic/gameConfig.properties";

    static{
        gameConfig = new Properties();
        //gameConfig.load(new FileReader(GAME_CONFIG_FILE));
        playerNames = new String[2];
        jEventManager = new JEventManager();
    }

    public static void connect() throws IOException{
        //jConnection = new JConnection(gameConfig.getProperty("SERVER_IP"),Integer.parseInt(gameConfig.getProperty("SERVER_PORT")));
        jConnectionManager = new JConnectionManager("127.0.0.1", 5656, jEventManager);
        jConnectionManager.run();
        jEventManager.notifyAllConsumers(JEventType.CONNECT, "connected");
    }

    public static JConnectionManager getjConnectionManager() {
        return jConnectionManager;
    }

    public static void setjConnectionManager(JConnectionManager jConnectionManager) {
        GameManager.jConnectionManager = jConnectionManager;
    }

    public static String[] getPlayerNames() {
        return playerNames;
    }

    public static void setPlayer1Name(String p1Name){
        playerNames[0] = p1Name;
    }

    public static void setPlayer2Name(String p2Name){
        playerNames[1] = p2Name;
    }

    public static void setPlayerNames(String[] playerNames) {
        GameManager.playerNames = playerNames;
    }

    public static Properties getGameConfig() {
        return gameConfig;
    }

    public static void setGameConfig(Properties gameConfig) {
        GameManager.gameConfig = gameConfig;
    }

    public static String getGameSessionID() {
        return gameSessionID;
    }

    public static void setGameSessionID(String gameSessionID) {
        GameManager.gameSessionID = gameSessionID;
    }

    public static String getGameName() {
        return gameName;
    }

    public static void setGameName(String gameName) {
        GameManager.gameName = gameName;
    }

    public static void bind(JEventCode eventCode, JEventConsumer jEventConsumer){
        getjEventManager().bind(eventCode, jEventConsumer);
    }

    public static void bindOnConnect(JEventConsumer jEventConsumer){
        bind(JEventType.CONNECT, jEventConsumer);
    }

    public static JEventManager getjEventManager() {
        return jEventManager;
    }

    public static void close(){
        if(jConnectionManager!=null){
            jConnectionManager.close();
        }
    }

    public static GameMode getGameMode() {
        return gameMode;
    }

    public static void setGameMode(GameMode gameMode) {
        GameManager.gameMode = gameMode;
    }
}
