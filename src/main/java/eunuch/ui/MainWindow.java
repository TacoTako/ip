package eunuch.ui;

import eunuch.ConnivingEunuch;
import eunuch.ui.element.DialogBox;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ConnivingEunuch eunuch;

    private final Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/king.png"));
    private final Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/eunuch.png"));

    @FXML
    public void initialize() {
        userInput.setPromptText("Type here...");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Eunuch instance */
    public void setAgent(ConnivingEunuch e) {
        eunuch = e;
        eunuch.connectUiWindow(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        printUserMsg(input);

        boolean isExit = eunuch.respond(input);
        userInput.clear();

        if (isExit) {
            try {
                handleClose();
            } catch (InterruptedException e) {
                // just close immediately
                Platform.exit();
            }
        }
    }

    /**
     * Shows a dialog box from the Eunuch
     * @param msg string to be displayed
     */
    @FXML
    public void printEunuchMsg(String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(msg, dukeImage)
        );
    }

    /**
     * Shows a dialog box from the User
     * @param msg string to be displayed
     */
    @FXML
    public void printUserMsg(String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(msg, userImage)
        );
    }

    /** Handles behaviours that needs to happen before program exits*/
    @FXML
    private void handleClose() throws InterruptedException {
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));

        pause.setOnFinished(e -> {
            Platform.exit();
        });

        pause.play();
    }
}
