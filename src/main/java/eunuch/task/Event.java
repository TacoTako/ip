package eunuch.task;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

import eunuch.parser.DateParser;

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
        return "EÐ" + this.taskDesc + "Ð" + isDone + "Ð"
                + DateParser.dateToParsableString(fromTime) + "Ð"
                        + DateParser.dateToParsableString(toTime);
    }

    /**
     * Compares task with task first, then by start time, then by end time,
     * then by description
     * @param task the object to be compared.
     */
    @Override
    public int compareTo(Task task) {
        int taskCompareVal  = super.compareTo(task);

        if (taskCompareVal != 0) {
            return taskCompareVal;
        }

        //task is an Event of same marked-ness
        Event event = (Event) task;

        int startCompare = DateParser.toDateTime(this.fromTime).compareTo(
                DateParser.toDateTime(event.fromTime));
        if (startCompare != 0) {
            return startCompare;
        }

        int endCompare = DateParser.toDateTime(this.toTime).compareTo(
                DateParser.toDateTime(event.toTime));
        if (endCompare != 0) {
            return endCompare;
        }

        return this.taskDesc.compareTo(task.taskDesc);
    }
}
