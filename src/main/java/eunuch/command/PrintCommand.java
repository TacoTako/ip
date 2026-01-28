package eunuch.command;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

public class PrintCommand extends Command {
    private final String msg;

    public PrintCommand(String msg) {
        super(false);
        this.msg = msg;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.displayText(msg);
    }
}
