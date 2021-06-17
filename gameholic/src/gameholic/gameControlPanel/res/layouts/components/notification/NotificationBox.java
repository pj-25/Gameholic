package gameholic.gameControlPanel.res.layouts.components.notification;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class NotificationBox {


    @FXML
    private HBox notificationBox;
    @FXML
    private Pane content;
    @FXML
    private Button clearBtn;

    public HBox getNotificationBox() {
        return notificationBox;
    }

    public void setNotificationBox(HBox notificationBox) {
        this.notificationBox = notificationBox;
    }

    public Pane getContent() {
        return content;
    }

    public void setContent(Pane content) {
        this.content = content;
    }

    public void addContentNode(Node node){
        content.getChildren().add(node);
    }

    public Button getClearBtn() {
        return clearBtn;
    }

    public void setClearBtn(Button clearBtn) {
        this.clearBtn = clearBtn;
    }
}
