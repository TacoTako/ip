package eunuch.task;

public class TaskStub extends Task {
    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String toData() {
        return "";
    }
}
