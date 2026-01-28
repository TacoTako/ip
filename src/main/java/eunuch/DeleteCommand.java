package eunuch;

import eunuch.storage.Storage;
import eunuch.task.Task;
import eunuch.task.TaskList;
import eunuch.ui.Ui;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.get(index);
        taskList.delete(index);
        ui.displayText("Got it. I've deleted this task:\n" + task +
                "\nThere are " + taskList.size() + " tasks in your list now.");
    }
}
