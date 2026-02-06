package eunuch.task;

import java.time.temporal.Temporal;

import eunuch.parser.DateParser;

/**
 * Represents a deadline task, with a due date
 */
public class Deadline extends Task {

    protected Temporal byTime;

    /**
     * Initializes a deadline with a due date passed as a LocalDate/LocalDateTime
     * @param description description of task
     * @param by due date of task
     */
    public Deadline(String description, Temporal by) {
        super(description);
        this.byTime = by;
    }

    /**
     * Initializes a deadline with a due date passed as a String to be parsed
     * @param description description of task
     * @param by due date of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.byTime = DateParser.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateParser.dateToString(byTime) + ")";
    }

    @Override
    public String toData() {
        return "DÐ" + this.taskDesc + "Ð" + isDone + "Ð"
                + DateParser.dateToParsableString(byTime);
    }
}
