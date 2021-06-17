package gameholic.gameControlPanel.res.layouts;

import gameholic.GameManager;
import gameholic.gameControlPanel.Main;
import gameholic.gameControlPanel.res.layouts.components.notification.Notifications;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.HashMap;

public class GameControlPanel {

    @FXML
    private Button connectBtn;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu themeMenu;
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
    @FXML
    private MenuItem disconnectMenuItem;

    private static Label statusMessage;

    @FXML
    private Menu TabsSelectMenu;
    private final HashMap<String, Tab> tabMap = new HashMap();

    private static TitledPane notifications;
    private static Paint defaultFill;
    public final static String COMPONENTS_DIR = "/gameholic/gameControlPanel/res/layouts/components/";
    public final static String STYLESHEETS_DIR = "/gameholic/gameControlPanel/res/stylesheets";

    private static final BooleanProperty connectionProperty = new SimpleBooleanProperty(false);

    public void initialize(){
        statusMessage = statusMessageLbl;

        addTab("Get Started", "GetStarted");
        addTab("Launcher", "Launcher");
        addTab("Game Store", "GameStore");

        try {
            initNotifications();
            chatWindow.setContent(FXMLLoader.load(getClass().getResource(COMPONENTS_DIR +"ChatWindow.fxml")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        loadAllStylesheets();

        disconnectMenuItem.disableProperty().bind(connectionProperty.not());

    }

    public void initNotifications() throws IOException {
        notificationsPane.setContent(FXMLLoader.load(getClass().getResource(COMPONENTS_DIR + "/notification/Notifications.fxml")));
        notifications = notificationsPane;
        defaultFill = notificationsPane.getTextFill();
        notificationsPane.setOnMouseReleased((mouseEvent)->{
            if(notificationsPane.isExpanded() || !notificationsPane.getText().equals("Notifications")){
                notificationsPane.setTextFill(defaultFill);
                notificationsPane.setText("Notifications");
                Notifications.setUnreadMsgCount(0);
            }
        });
    }

    @FXML
    public void connect(ActionEvent actionEvent) {
        try{
            GameManager.connect();
            setStatusMessage("Connection established :)");
            connectionProperty.set(true);
            GameManager.getjConnectionManager().bindOnClose((eventData)->{
                connectionProperty.set(false);
            });
            Main.getPrimaryStage().setOnCloseRequest((windowEvent)->{
                GameManager.getjConnectionManager().close();
            });
            GameManager.setPlayer1Name(playerName.getText());

            connectBtn.disableProperty().bind(connectionProperty);
            playerName.disableProperty().bind(connectionProperty);

            Notifications.add("Welcome "+playerName.getText()+" :)");
        }catch (IOException ioException){
            setStatusMessage("Enable to connect to server :(, Please check network connection!");
        }
    }


    public boolean addTab(String tabName, String layout){
        if(!tabMap.containsKey(tabName)) {
            try {
                Tab tab = new Tab(tabName);
                tab.setContent(FXMLLoader.load(getClass().getResource(COMPONENTS_DIR + layout + ".fxml")));
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

    public void loadAllStylesheets(){
        ToggleGroup themeOptionsGrp = new ToggleGroup();
        //String[] stylesheets = new File(STYLESHEETS_DIR).list();
        String[] stylesheets = {"Default.css", "Light.css", "PlainDark.css"};
        if(stylesheets!=null){
            for(String theme:stylesheets){
                RadioMenuItem themeOption = new RadioMenuItem(theme.substring(0, theme.length()-4));
                themeMenu.getItems().add(themeOption);
                themeOption.setToggleGroup(themeOptionsGrp);
                themeOption.setOnAction((actionEvent)->{
                    if(themeOption.isSelected()){
                        Main.setTheme(themeOption.getText());
                    }
                });
                if(theme.equals("Default.css")){
                    themeOption.setSelected(true);
                }
            }
        }
    }

    public static void updateNotifications(int count){
        if(count>0){
            notifications.setText("* Notifications ("+count+")");
            notifications.setTextFill(Color.GREEN);
            //notifications.setExpanded(true);
        }
    }

    public static String getStatusMessage(){
        return statusMessage.getText();
    }

    public static void setStatusMessage(String msg){
        statusMessage.setText(msg);
    }

    public void contactDeveloper(ActionEvent actionEvent) {
        setStatusMessage("Wait! Jumping to Web browser...");
        Main.openInBrowser("https://mail.google.com/mail/u/0/?fs=1&to=joshiprashant.jp25@gmail.com&su=Contact%20Me&body=Write%20your%20message%20here...&tf=cm");
    }

    public void openGithubRepo(ActionEvent actionEvent) {
        setStatusMessage("Wait! Jumping to Web browser...");
        Main.openInBrowser("https://github.com/pj-25/Gameholic");
    }

    public void close(ActionEvent actionEvent) {
        Main.getPrimaryStage().close();
    }

    public void onNameInput(KeyEvent keyEvent) {
        connectBtn.setDisable(playerName.getText().length()==0);
    }

    public static BooleanProperty connectionProperty() {
        return connectionProperty;
    }

    public static void setConnectionProperty(boolean connectionProperty) {
        GameControlPanel.connectionProperty.set(connectionProperty);
    }

    public static boolean isConnected(){
        return connectionProperty.get();
    }

    public static void disconnect(){
        if(isConnected()){
            GameManager.getjConnectionManager().close();
            connectionProperty.set(false);
            setStatusMessage("Disconnected!");
        }
    }

    public void disconnect(ActionEvent actionEvent) {
        disconnect();
        connectBtn.disableProperty().unbind();
    }
}
