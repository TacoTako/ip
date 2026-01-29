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
        return "T " + this.taskDesc + " " + isDone;
    }
}
