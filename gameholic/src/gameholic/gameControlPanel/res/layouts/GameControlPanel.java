package gameholic.gameControlPanel.res.layouts;

import gameholic.gameControlPanel.GameManager;
import gameholic.gameControlPanel.Main;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashMap;

public class GameControlPanel {

    @FXML
    private Button connectBtn;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TabPane tabPane;
    @FXML
    private TitledPane notificationsPane;
    @FXML
    private TitledPane chatWindow;
    @FXML
    private TextField playerName;
    @FXML
    private Label statusMessageLbl;

    private static Label statusMessage;

    @FXML
    private Menu TabsSelectMenu;
    private final HashMap<String, Tab> tabMap = new HashMap();

    public final static String COMPONENTS_PATH = "/gameholic/gameControlPanel/res/layouts/components/";

    private static final BooleanProperty connectionProperty = new SimpleBooleanProperty(false);

    public void initialize(){
        statusMessage = statusMessageLbl;
        addTab("Get Started", "GetStarted");
        addTab("Launcher", "Launcher");
        addTab("Game Store", "GameStore");
        try {
            notificationsPane.setContent(FXMLLoader.load(getClass().getResource(COMPONENTS_PATH+ "Notifications.fxml")));
            chatWindow.setContent(FXMLLoader.load(getClass().getResource(COMPONENTS_PATH +"ChatWindow.fxml")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void connect(ActionEvent actionEvent) {

    }


    public boolean addTab(String tabName, String layout){
        if(!tabMap.containsKey(tabName)) {
            try {
                Tab tab = new Tab(tabName);
                tab.setContent(FXMLLoader.load(getClass().getResource(COMPONENTS_PATH + layout + ".fxml")));
                tabPane.getTabs().add(tab);
                tabMap.put(tabName, tab);

                CheckMenuItem checkMenuItem = new CheckMenuItem(tabName);
                TabsSelectMenu.getItems().add(checkMenuItem);
                checkMenuItem.setSelected(true);
                tab.setOnClosed((closeEvent)->{
                    tabMap.remove(tabName);
                    checkMenuItem.setSelected(false);
                });
                checkMenuItem.setOnAction((selectEvent)->{
                    if(!tabMap.containsKey(tabName)){
                        tabPane.getTabs().add(tab);
                        tabMap.put(tabName, tab);
                    }else{
                        tabPane.getTabs().remove(tab);
                        tabMap.remove(tabName);
                    }
                });
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getStatusMessage(){
        return statusMessage.getText();
    }

    public static void setStatusMessage(String msg){
        statusMessage.setText(msg);
    }

    public void contactDeveloper(ActionEvent actionEvent) {
        Main.openInBrowser("https://mail.google.com/mail/u/0/?fs=1&to=joshiprashant.jp25@gmail.com&su=Contact%20Me&body=Write%20your%20message%20here...&tf=cm");
    }

    public void openGithubRepo(ActionEvent actionEvent) {
        Main.openInBrowser("https://github.com/pj-25/Gameholic");
    }

    public void close(ActionEvent actionEvent) {
        Main.getPrimaryStage().close();
    }

    public static boolean isConnectionProperty() {
        return connectionProperty.get();
    }

    public static BooleanProperty connectionPropertyProperty() {
        return connectionProperty;
    }

    public static void setConnectionProperty(boolean connectionProperty) {
        GameControlPanel.connectionProperty.set(connectionProperty);
    }
}
