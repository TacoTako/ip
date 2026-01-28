package eunuch.task;

import eunuch.parser.DateParser;

import java.time.temporal.Temporal;

public class Deadline extends Task {

    protected Temporal by;

    public Deadline(String description, Temporal by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = DateParser.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                DateParser.dateToString(by) + ")";
    }

    @Override
    public String toData() {
        return "D " + this.taskDesc + " " + isDone + " " +
                DateParser.dateToParsableString(by);
    }


}