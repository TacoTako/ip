package eunuch.command;

import java.time.temporal.Temporal;

import eunuch.storage.Storage;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

/**
 * Represents a command that searches the list for task that match
 */
public class SearchCommand extends Command {
    private final String searchPhrase;
    //unused for now
    private final Temporal dueBeforeDate;

    /**
     * Initialises search command with parameters
     * @param searchPhrase string to be matched in the task
     * @param dueBeforeDate latest time that searched task occurs, this can be null
     */
    public SearchCommand(String searchPhrase, Temporal dueBeforeDate) {
        super(false);
        this.searchPhrase = searchPhrase;
        this.dueBeforeDate = dueBeforeDate;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.displayText(taskList.toString());
    }
}
