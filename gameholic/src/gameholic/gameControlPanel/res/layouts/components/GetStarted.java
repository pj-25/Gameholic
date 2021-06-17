package gameholic.gameControlPanel.res.layouts.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GetStarted {
    @FXML
    private Pagination pagination;

    public void initialize() throws IOException {
        VBox views = FXMLLoader.load(getClass().getResource("/gameholic/gameControlPanel/res/layouts/components/GetStartedViews.fxml"));
        pagination.setPageCount(views.getChildren().size());
        HBox[] viewBoxes = new HBox[views.getChildren().size()];
        views.getChildren().toArray(viewBoxes);
        pagination.setPageFactory((pageIndex)-> viewBoxes[pageIndex]);
    }
}
