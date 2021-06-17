package gameholic.gameControlPanel.res.layouts.components;

import gameholic.GameManager;
import gameholic.gameControlPanel.GameEvent;
import gameholic.gameControlPanel.Main;
import gameholic.gameControlPanel.res.layouts.GameControlPanel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import jsc.jMessageHandler.JMessageFormatHandler;

public class Launcher {

    @FXML
    private Button notifyJoinBtn;
    @FXML
    private TextField opponentName;
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
        String[] gameNameList = {"CrazyBall"};
        for (String game:gameNameList){
            gameName.getItems().add(game);
        }

    }

    public void toGmail(ActionEvent actionEvent) {
        String link = "https://mail.google.com/mail/u/0/?fs=1&to=OPPONENT@gmail.com&su= Invitation from "+ GameManager.getPlayerNames()[0]+"&body=Hello gameholic!%0aJoin me to play "+gameName.getValue()+":)%0aGame Session ID: "+createGSessionID.getText()+"%0a%0aNote: Join using above session Id!%0aWaiting for you to join...&tf=cm";
        GameControlPanel.openInBrowser(link);
    }

    public void toWhatsapp(ActionEvent actionEvent) {
        GameControlPanel.openInBrowser("https://api.whatsapp.com/send?&text=Invitation from "+ GameManager.getPlayerNames()[0]+"%0aHello gameholic!%0a Join me to play "+gameName.getValue()+":)%0aGame Session ID: "+createGSessionID.getText()+"%0aNote: Join using above session Id!%0aWaiting for you to join...");
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
            launch(gameName.getValue());
        }
    }

    public void launch(String gameName) {
        GameManager.setGameName(gameName);
        Platform.runLater(()->{
            GameControlPanel.setStatusMessage("Be ready! Launching "+gameName);
        });
    }

    public void onOpponentNameInput(InputMethodEvent inputMethodEvent) {
        notifyJoinBtn.setDisable(opponentName.getText().length()==0);
    }

    public void notifyJoin(ActionEvent actionEvent) {
        GameManager.getjConnectionManager().send(JMessageFormatHandler.encode(createGSessionID.getText(), opponentName.getText()));
    }

    public void create(ActionEvent actionEvent) {
        if(gameName.getValue()!=null){
            GameManager.getjConnectionManager().send(GameEvent.CREATE_GSESSION, JMessageFormatHandler.encode(gameName.getValue(), GameManager.getPlayerNames()[0]));
            GameManager.bind(GameEvent.CREATE_GSESSION, (data)->{
                gameSessionInfoPane.setDisable(false);
                String[] gData = JMessageFormatHandler.decode(data);
                createGSessionID.setText(gData[0]);
                Platform.runLater(()->{
                    GameControlPanel.setStatusMessage("Game session created successfully!");
                });
            });
            createBtn.setDisable(true);
            GameManager.getjConnectionManager().bindOnClose((s -> {
                createBtn.setDisable(false);
                gameSessionInfoPane.setDisable(false);
            }));
            onStartGame();
            GameControlPanel.setStatusMessage("Requested to create new game session, please wait!");
        }
        else{
            Main.alert(Alert.AlertType.ERROR, "Please select game");
            gameName.requestFocus();
        }
    }

    public void join(ActionEvent actionEvent) {
        joinBtn.setDisable(true);
        GameManager.getjConnectionManager().send(GameEvent.JOIN_GSESSION, JMessageFormatHandler.encode(joinGSessionID.getText(), GameManager.getPlayerNames()[0]));
        GameControlPanel.setStatusMessage("Join request sent...");
        GameManager.bind(GameEvent.INVALID_SESSION_ID, eventData->{
            Main.alert(Alert.AlertType.ERROR, "Invalid Game Session ID :( \nPlease contact your friend for the same!");
            Platform.runLater(()->{
                joinGSessionID.requestFocus();
                GameControlPanel.setStatusMessage("Join request rejected due to invalid Game session ID");
            });
        });
        onStartGame();
    }

    public void onStartGame(){
        GameManager.bind(GameEvent.START_GAME, eventData -> {
            String[] gData = JMessageFormatHandler.decode(eventData);
            GameManager.setPlayer2Name(gData[1]);
            launch(gData[0]);
            GameManager.bind(GameEvent.END_GSESSION, (endEventData)->{
                GameControlPanel.setStatusMessage("Game Ended!");
            });
            GameManager.bind(GameEvent.GAME_OVER, (gameOverEvent)->{
                GameControlPanel.setStatusMessage("Game Over!");
            });
        });
    }
}
