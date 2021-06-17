package gameholic.gameControlPanel.res.layouts.components;

import crazyBallGame.CrazyBallGame;
import gameholic.GameManager;
import gameholic.gameControlPanel.Main;
import gameholic.gameControlPanel.res.layouts.GameControlPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Launcher {

    @FXML
    private ComboBox<String> gameName;
    @FXML
    private HBox onlineModePane;
    @FXML
    private Button createBtn;
    @FXML
    private GridPane gameSessionInfoPane;
    @FXML
    private TextField createGSessionID;
    @FXML
    private TextField joinGSessionID;
    @FXML
    private Button joinBtn;
    @FXML
    private TextField player1Name;
    @FXML
    private TextField player2Name;
    @FXML
    private Button startGameBtn;


    public void initialize(){
        loadGameNames();

        onlineModePane.disableProperty().bind(GameControlPanel.connectionProperty().not());
    }

    private void loadGameNames() {
    }


    public void toGmail(ActionEvent actionEvent) {
        String link = "https://mail.google.com/mail/u/0/?fs=1&to=OPPONENT@gmail.com&su= Invitation from "+ GameManager.getPlayerNames()[0]+"&body=Hello gameholic!\n Join me to play :)\nGame Session ID: "+createGSessionID.getText()+"\nNote: Join using above session Id!\nWaiting for you to join...&tf=cm";
        Main.openInBrowser(link);
    }

    public void toWhatsapp(ActionEvent actionEvent) {

    }

    public void onJoinGSessionIDInput(KeyEvent keyEvent) {
        joinBtn.setDisable(joinGSessionID.getText().length()==0);
    }

    public void startGame(ActionEvent actionEvent) {
        if(player1Name.getText().isEmpty()){
            Main.alert(Alert.AlertType.ERROR, "Player 1 name is required field!");
            player1Name.requestFocus();
        }
        else if(player2Name.getText().isEmpty()){
            Main.alert(Alert.AlertType.ERROR, "Player 2 name is required field!");
        }
        else{
            start(gameName.getValue());
        }
    }

    public void start(String gameName) {
        CrazyBallGame.main(new String[0]);
    }
}
