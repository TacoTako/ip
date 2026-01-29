package eunuch.command;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

/**
 * Represents a command that will perform code when called
 */
public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Performs actions to interact with the passed parameters
     * @param taskList task list that can be modified
     * @param storage file read and writer
     * @param ui displays text to the user
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);
}
