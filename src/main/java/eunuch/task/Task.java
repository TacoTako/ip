package eunuch.task;

/**
 * Represents a single task with a description
 */
public abstract class Task {
    protected boolean isDone = false;
    protected final String taskDesc;

    public Task(String description) {
        this.taskDesc = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String check = (isDone) ? "X" : " ";
        return "[" + check + "] " + taskDesc;
    }

    public abstract String toData();
}
