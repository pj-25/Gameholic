package gameholic.gameControlPanel;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;
    private final static ClipboardContent clipboardContent = new ClipboardContent();
    private static HostServices hostServices;
    public static void main(String []s){
        launch(s);
    }

    @Override
    public void start(Stage primaryStage){
        setPrimaryStage(primaryStage);
        hostServices = getHostServices();

        openView("GameControlPanel");

        primaryStage.setTitle("Gameholic: Game control panel");
        primaryStage.show();
    }


    public static void openView(String viewPath){
        Platform.runLater(()->{
            try {
                Parent root = FXMLLoader.load(Main.class.getClass().getResource("/gameholic/gameControlPanel/res/layouts/" +viewPath+".fxml"));
                Scene scene = new Scene(root);
                //scene.getStylesheets().add("/gameholic/gameControlPanel/res/stylesheets/default.css");
                //JMetro jMetro = new JMetro(Style.DARK);
                //jMetro.setScene(scene);
                getPrimaryStage().setScene(scene);
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        });
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    public static void copyToClipboard(String content){
        clipboardContent.putString(content);
    }

    public static ClipboardContent getClipboardContent(){
        return clipboardContent;
    }


    public static void openInBrowser(String uri){
        hostServices.showDocument(uri);
    }
}
