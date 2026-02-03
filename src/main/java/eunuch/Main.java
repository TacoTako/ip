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
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setAgent(eunuch);
            stage.show();

            eunuch.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
