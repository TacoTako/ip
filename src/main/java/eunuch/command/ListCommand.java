package eunuch.command;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

/**
 * Represents a command that will print the task list
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.displayText(taskList.toString());
    }
}
