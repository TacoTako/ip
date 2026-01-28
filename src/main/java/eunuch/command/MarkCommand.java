package eunuch.command;

import eunuch.storage.Storage;
import eunuch.task.Task;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

public class MarkCommand extends Command {
    private int index;
    private boolean toBeMarked;

    public MarkCommand(int index, boolean toBeMarked) {
        super(false);
        this.index = index;
        this.toBeMarked = toBeMarked;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.get(index);

        if (toBeMarked) {
            if (task.isDone()) {
                ui.displayText("Task is already marked as done: \n" + task);
            } else {
                task.markDone();
                ui.displayText("I've marked this task as done: \n" + task);
            }
        } else {
            if (!task.isDone()) {
                ui.displayText("Task is already marked as undone: \n" + task);
            } else {
                task.unmarkDone();
                ui.displayText("I've marked this task as not done: \n" + task);
            }
        }
    }
}
