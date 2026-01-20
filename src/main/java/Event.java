public class Event extends Task {

    protected String fromTime;
    protected String toTime;

    public Event(String description, String from, String to) {
        super(description);
        this.fromTime = from;
        this.toTime = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +  " (from: " + fromTime + " to: " + toTime + ")";
    }
}