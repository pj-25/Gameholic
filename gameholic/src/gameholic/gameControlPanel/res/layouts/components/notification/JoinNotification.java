package gameholic.gameControlPanel.res.layouts.components.notification;

import gameholic.gameControlPanel.GameEvent;
import gameholic.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jsc.jMessageHandler.JMessageFormatHandler;

public class JoinNotification {
    @FXML
    private Label invitationMsg;
    @FXML
    private Button joinBtn;
    @FXML
    public VBox joinNotification;

    public VBox createJoinNotification(String inviterName, String gameName, String gameSessionID){
        invitationMsg.setText(inviterName +" invites you to join "+ gameName);
        joinBtn.setOnAction((actionEvent)->{
            String msg = JMessageFormatHandler.encode(gameSessionID, GameManager.getPlayerNames()[0]);
            GameManager.getjConnectionManager().send(GameEvent.JOIN_GSESSION, msg);
        });
        return joinNotification;
    }
}
