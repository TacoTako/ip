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
    private static final int MIN_SCREEN_WIDTH = 480;
    private static final int MIN_SCREEN_HEIGHT = 220;
    private static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
    private static final String WINDOW_NAME = "Conniving Eunuch";

    private final ConnivingEunuch eunuch = new ConnivingEunuch();


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setMinHeight(MIN_SCREEN_HEIGHT);
            stage.setWidth(MIN_SCREEN_WIDTH);
            stage.setMinWidth(MIN_SCREEN_WIDTH);
            stage.setTitle(WINDOW_NAME);

            fxmlLoader.<MainWindow>getController().setAgent(eunuch);

            stage.setScene(scene);
            stage.show();

            eunuch.initializeAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
