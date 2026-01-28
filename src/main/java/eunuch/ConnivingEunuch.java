package eunuch;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

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
            command.execute(taskList, storage, ui);
            endConversation = command.isExit();
        }


    }
}
