package eunuch.task;

/**
 * Represents a single task with a description
 */
public abstract class Task implements Comparable<Task> {
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

    /**
     * Sorts task by doneness, then task type
     * @param task the object to be compared.
     */
    @Override
    public int compareTo(Task task) {
        if (this.isDone && !task.isDone) {
            return 1;
        } else if (!this.isDone && task.isDone) {
            return -1;
        }

        int classOrder = this.getTaskOrder() - task.getTaskOrder();
        return classOrder;
    }

    private int getTaskOrder() {
        if (this instanceof Todo) {
            return 1;
        }

        if (this instanceof Deadline) {
            return 2;
        }

        if (this instanceof Event) {
            return 3;
        }
        return 0;
    }
}
