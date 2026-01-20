public class Task {
    private boolean isDone = false;
    private final String taskDesc;

    public Task(String desc) {
        this.taskDesc = desc;
    }

    public boolean getDone() {
        return isDone;
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
}
