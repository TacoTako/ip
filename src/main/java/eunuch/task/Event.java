package eunuch.task;

import eunuch.parser.DateParser;

import java.time.temporal.Temporal;

public class Event extends Task {

    protected Temporal fromTime;
    protected Temporal toTime;

    public Event(String description, Temporal from, Temporal to) {
        super(description);
        this.fromTime = from;
        this.toTime = to;
    }

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