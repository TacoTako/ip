package eunuch;

import java.io.IOException;

import eunuch.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Eunuch using FXML.
 */
public class Main extends Application {

    private final ConnivingEunuch eunuch = new ConnivingEunuch();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setMinHeight(220);
            stage.setWidth(480);
            stage.setMinWidth(480);
            stage.setScene(scene);

            stage.setTitle("Conniving Eunuch");

            fxmlLoader.<MainWindow>getController().setAgent(eunuch);
            stage.show();

            eunuch.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
