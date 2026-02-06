package eunuch.command;

import java.io.IOException;

import eunuch.storage.Storage;
import eunuch.task.Task;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

/**
 * Represents a command that will delete a task from the task list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for a delete command
     * @param index 1-indexed item in the list to be deleted
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Deletes the task from the list and prints the task deleted + tasks left
     * @param taskList task list that can be modified
     * @param storage file read and writer
     * @param ui displays text to the user
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.get(index);
        taskList.delete(index);

        try {
            storage.writeListToFile(taskList);
        } catch (IOException e) {
            ui.displayText("Error in saving list");
        }

        ui.displayText("Immediately. I've deleted this task:\n" + task
                + "\nThere are " + taskList.size() + " tasks in your list now.");
    }
}
