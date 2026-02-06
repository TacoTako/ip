package eunuch.command;

import java.io.IOException;

import eunuch.storage.Storage;
import eunuch.task.Task;
import eunuch.task.TaskList;
import eunuch.ui.Ui;


/**
 * Represents a command that will add a task into the task list
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for a command
     * @param task the task object that will be added to the list when executing
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.add(task);

        try {
            storage.writeListToFile(taskList);
        } catch (IOException e) {
            ui.displayText("Error in saving list");
        }

        ui.displayText("Delightful. I've added this task:\n" + task
                + "\nThere are " + taskList.size() + " tasks in your list now.");
    }
}
