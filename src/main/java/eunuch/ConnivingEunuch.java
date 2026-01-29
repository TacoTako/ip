package eunuch;

import java.util.Scanner;

import eunuch.command.Command;
import eunuch.command.PrintCommand;
import eunuch.parser.Parser;
import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

/**
 * Represents the chatbot that will manage user's tasks
 */
public class ConnivingEunuch {
    private static TaskList taskList = new TaskList();
    private static final Storage storage = new Storage("data/eunuch.txt");
    private static final Ui ui = new Ui();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean endConversation = false;


        taskList = storage.loadListFromFile();
        ui.greet();

        //while loop for listening to users
        while (!endConversation) {
            String input = s.nextLine();
            Command command = Parser.parse(input);

            try {
                command.execute(taskList, storage, ui);
            } catch (ConnivingException e) {
                new PrintCommand(e.getMessage()).execute(taskList, storage, ui);
            }

            endConversation = command.isExit();
        }


    }
}
