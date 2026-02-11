package eunuch.task;

/**
 * Represents a task with a description
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "TÐ" + this.taskDesc + "Ð" + isDone;
    }

    @Override
    public int compareTo(Task task) {
        int taskCompareVal = super.compareTo(task);

        if (taskCompareVal != 0) {
            return taskCompareVal;
        }

        return this.taskDesc.compareTo(task.taskDesc);
    }
}
