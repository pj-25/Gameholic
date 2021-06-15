package gameholic.gameControlPanel.res.layouts;

import gameholic.gameControlPanel.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

public class GameControlPanel {

    @FXML
    private Button connectBtn;
    @FXML
    private Hyperlink contactDeveloper;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
    @FXML
    private TitledPane notificationsPane;
    @FXML
    private TitledPane chatWindow;
    @FXML
    private TextField playerName;
    @FXML
    private Label statusMessageLbl;

    private static Label statusMessage;


    public void initialize(){
        statusMessage = statusMessageLbl;
        addTab("Get Started", "GetStarted");
        addTab("Launcher", "Launcher");
        addTab("Game Store", "GameStore");

    }

    @FXML
    public void connect(ActionEvent actionEvent) {

    }

    public void addTab(String tabName, String layout){
        try{
            Tab tab = new Tab(tabName);
            tab.setContent(FXMLLoader.load(getClass().getResource("/gameholic/gameControlPanel/res/layouts/components/" +layout+".fxml")));
            tabPane.getTabs().add(tab);
        }catch (IOException e){
            e.printStackTrace();
        }
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
}
