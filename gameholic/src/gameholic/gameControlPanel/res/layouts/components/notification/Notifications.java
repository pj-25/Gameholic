package gameholic.gameControlPanel.res.layouts.components.notification;

import gameholic.gameControlPanel.res.layouts.GameControlPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Notifications {
    @FXML
    private VBox notificationVBox;
    @FXML
    private Button clearAllBtn;

    private static VBox notificationArea;

    public static int unreadMsgCount = 0;
    public static String NOTIFICATION_DIR = "/gameholic/gameControlPanel/res/layouts/components/notification/";

    public void initialize(){
        notificationArea = notificationVBox;
        clearAllBtn.setOnAction((actionEvent)->{
            clearAll();
        });
    }

    public static void add(String data) throws IOException{
        add(new Label(data));
    }

    public static void add(Node node) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Notifications.class.getResource(NOTIFICATION_DIR+"NotificationBox.fxml"));
        Parent notificationBox = fxmlLoader.load();
        VBox content = (VBox) notificationBox.lookup("#content");
        content.getChildren().add(node);
        Button clearButton = (Button)notificationBox.lookup("#clearBtn");
        clearButton.setOnAction((clearAction)->{
            notificationArea.getChildren().remove(notificationBox);
        });
        unreadMsgCount++;
        GameControlPanel.updateNotifications(unreadMsgCount);
        notificationArea.getChildren().add(notificationBox);
    }

    public static void clearAll(){
        notificationArea.getChildren().clear();
    }


    public static VBox getNotificationArea() {
        return notificationArea;
    }

    public static int getUnreadMsgCount() {
        return unreadMsgCount;
    }

    public static void setUnreadMsgCount(int unreadMsgCount) {
        Notifications.unreadMsgCount = unreadMsgCount;
    }

    public void clearAll(ActionEvent actionEvent) {
        clearAll();
    }
}
