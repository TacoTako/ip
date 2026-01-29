package eunuch.command;

import eunuch.storage.Storage;
import eunuch.task.Task;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.add(task);
        ui.displayText("Got it. I've added this task:\n" + task
                + "\nThere are " + taskList.size() + " tasks in your list now.");
    }
}
