package eunuch;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList taskList, Storage storage, Ui ui);
}
