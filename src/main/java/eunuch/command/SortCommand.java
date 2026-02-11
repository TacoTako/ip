package eunuch.command;

import java.io.IOException;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;


/**
 * Represents a command that will add a task into the task list
 */
public class SortCommand extends Command {
    private final boolean isPersistent;
    /**
     * Constructor for a command
     * @param isPersistent if true, sort the list, else output a new sorted list
     */
    public SortCommand(boolean isPersistent) {
        super(false);
        this.isPersistent = isPersistent;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        if (!isPersistent) {
            ui.displayText("calling");
            TaskList sortedList = taskList.getSorted();
            ui.displayText("Immediately. Here are your sorted tasks:\n" + sortedList);
            return;
        }

        taskList.sortSelf();
        try {
            storage.writeListToFile(taskList);
        } catch (IOException e) {
            ui.displayText("Error in saving list");
        }

        ui.displayText("Very good, I have sorted your list:\n" + taskList);
    }
}
