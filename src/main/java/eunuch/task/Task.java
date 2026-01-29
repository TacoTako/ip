package eunuch.task;

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

    /**
     * Returns if the task has a description that contains a string
     * @param toFind string sequence to check
     * @return if the task has a description that contains the input
     */
    public boolean contains(String toFind) {
        return taskDesc.toLowerCase().contains(toFind.toLowerCase());
    }

    @Override
    public String toString() {
        String check = (isDone) ? "X" : " ";
        return "[" + check + "] " + taskDesc;
    }

    public abstract String toData();
}
