package eunuch.command;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

/**
 * Represents a command that displays a message to the user
 */
public class PrintCommand extends Command {
    private final String msg;
    private final boolean isError;

    /**
     * Constructs a print command
     * @param msg message to be displayed
     */
    public PrintCommand(String msg, boolean isError) {
        super(false);
        this.msg = msg;
        this.isError = isError;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        if (isError) {
            ui.displayError(msg);
        } else {
            ui.displayText(msg);
        }
    }
}
