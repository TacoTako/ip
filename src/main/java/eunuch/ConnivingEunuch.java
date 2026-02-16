package eunuch;

import java.util.Scanner;

import eunuch.command.Command;
import eunuch.command.PrintCommand;
import eunuch.exception.ConnivingException;
import eunuch.parser.Parser;
import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.MainWindow;
import eunuch.ui.Ui;

import java.util.Scanner;

/**
 * Represents the chatbot logic that will manage user's tasks
 */
public class ConnivingEunuch {
    private TaskList taskList = new TaskList();
    private final Storage storage = new Storage("data/eunuch.txt");
    private Ui ui = new Ui(null);

    /** Links main window to Ui class, so that it can print messages to it*/
    public void connectUiWindow(MainWindow window) {
        ui = new Ui(window);
    }

    /**
     * Sets up the Eunuch on startup by loading data and greeting
     */
    public void initializeAttributes() {
        taskList = storage.loadListFromFile();
        assert ui != null;
        ui.greet();
    }

    /**
     * Handles internal logic using user input
     * @param input test input user has typed
     * @return true if the program is to exit, false otherwise
     */
    public boolean respondToInput(String input) {
        Command command = Parser.parse(input);

        assert command != null;
        execute(command);

        return command.isExit();
    }

    private void execute(Command command) {
        try {
            command.execute(taskList, storage, ui);
        } catch (ConnivingException e) {
            new PrintCommand(e.getMessage(), true).execute(taskList, storage, ui);
        }
    }

    /**
     * Starts the class in terminal
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean endConversation = false;

        ConnivingEunuch eunuch = new ConnivingEunuch();
        eunuch.ui.greet();

        //while loop for listening to users
        while (!endConversation) {
            String input = s.nextLine();
            Command command = Parser.parse(input);
            eunuch.execute(command);
            endConversation = command.isExit();
        }

        eunuch.ui.sayGoodbye();
    }
}
