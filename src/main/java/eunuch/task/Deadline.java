package eunuch.task;

import eunuch.parser.DateParser;

import java.time.temporal.Temporal;

/**
 * Represents a deadline task, with a due date
 */
public class Deadline extends Task {

    protected Temporal by;

    /**
     * Initializes a deadline with a due date passed as a LocalDate/LocalDateTime
     * @param description description of task
     * @param by due date of task
     */
    public Deadline(String description, Temporal by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes a deadline with a due date passed as a String to be parsed
     * @param description description of task
     * @param by due date of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateParser.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateParser.dateToString(by) + ")";
    }

    @Override
    public String toData() {
        return "D " + this.taskDesc + " " + isDone + " "
                + DateParser.dateToParsableString(by);
    }
}
