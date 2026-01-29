package eunuch.command;

import java.io.IOException;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            storage.writeListToFile(taskList);
        } catch (IOException e) {
            ui.displayText("Error in saving list");
        }
        ui.sayGoodbye();
    }
}
