package eunuch.task;

/**
 * Represents a stub class for a task with only description functionality
 */
public class TaskStub extends Task {
    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String toData() {
        return "";
    }
}
