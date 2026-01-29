package eunuch.task;

import eunuch.parser.DateParser;

import java.time.temporal.Temporal;

/**
 * Represents a task with a duration, a from time to an end time
 */
public class Event extends Task {

    protected Temporal fromTime;
    protected Temporal toTime;

    /**
     * Intialises an event with LocalDate/LocalDateTime objects
     * @param description Description of task
     * @param from start of event
     * @param to end of event
     */
    public Event(String description, Temporal from, Temporal to) {
        super(description);
        this.fromTime = from;
        this.toTime = to;
    }

    /**
     * Intialises an event with String representation of Temporal objects to be parsed
     * @param description Description of task
     * @param from start of event
     * @param to end of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.fromTime = DateParser.parse(from);
        this.toTime = DateParser.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + DateParser.dateToString(fromTime) + " to: "
                        + DateParser.dateToString(toTime) + ")";
    }

    @Override
    public String toData() {
        return "E " + this.taskDesc + " " + isDone + " "
                + DateParser.dateToParsableString(fromTime) + " "
                        + DateParser.dateToParsableString(toTime);
    }
}