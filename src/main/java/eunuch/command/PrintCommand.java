package eunuch.command;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

/**
 * Represents a command that displays a message to the user
 */
public class PrintCommand extends Command {
    private final String msg;

    /**
     * Constructs a print command
     * @param msg message to be displayed
     */
    public PrintCommand(String msg) {
        super(false);
        this.msg = msg;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.displayText(msg);
    }
}
