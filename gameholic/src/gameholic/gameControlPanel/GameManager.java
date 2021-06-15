package gameholic.gameControlPanel;

import jsc.jConnection.JConnection;
import jsc.jEventManager.JEventManager;

import java.io.IOException;
import java.util.Properties;

public class GameManager {
    private static JConnection jConnection;
    private static JEventManager jEventManager;
    private static String[] playerNames;

    private static Properties gameConfig;
    public static final String GAME_CONFIG_FILE = "/gameholic/gameConfig.properties";

    static{
        try {
            gameConfig = new Properties();
            //gameConfig.load(new FileReader(GAME_CONFIG_FILE));
            //jConnection = new JConnection(gameConfig.getProperty("SERVER_IP"),Integer.parseInt(gameConfig.getProperty("SERVER_PORT")));
            jConnection = new JConnection("127.0.0.1", 5656);
            jConnection.run();
            jEventManager = jConnection.getJEventManager();
            playerNames = new String[2];
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static JConnection getjConnection() {
        return jConnection;
    }

    public static void setjConnection(JConnection jConnection) {
        GameManager.jConnection = jConnection;
    }

    public static String[] getPlayerNames() {
        return playerNames;
    }

    public static void setPlayerNames(String[] playerNames) {
        GameManager.playerNames = playerNames;
    }

    public static Properties getGameConfig() {
        return gameConfig;
    }

    public static JEventManager getjEventManager() {
        return jEventManager;
    }

    public static void setGameConfig(Properties gameConfig) {
        GameManager.gameConfig = gameConfig;
    }
}
