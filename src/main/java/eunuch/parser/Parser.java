package eunuch.parser;

import eunuch.ConnivingException;
import eunuch.command.*;
import eunuch.task.Deadline;
import eunuch.task.Event;
import eunuch.task.Task;
import eunuch.task.Todo;

import java.time.temporal.Temporal;

public class Parser {
    public static Command parse(String input) {
        String[] temp = input.trim().split(" ", 2);
        String keyword = temp[0].toLowerCase();
        String content = (temp.length > 1) ? temp[1] : "";

        try {
            return switch (keyword) {
            case "" -> new PrintCommand("I must have not caught that, Your Highness.");
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> parseMark(content);
            case "unmark" -> parseUnmark(content);
            case "todo" -> parseTodo(content);
            case "deadline" -> parseDeadline(content);
            case "event" -> parseEvent(content);
            case "delete" -> parseDelete(content);
            default -> new PrintCommand("Your foolish servant does not understand.");
            };
        } catch (ConnivingException e) {
            return new PrintCommand(e.getMessage());
        }
    }

    private static Command parseMark(String content) throws ConnivingException {
        try {
            int index = Integer.parseInt(content);
            return new MarkCommand(index, true);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Input a number after the \"mark\" keyword.");
        }
    }

    private static Command parseUnmark(String content) throws ConnivingException {
        try {
            int index = Integer.parseInt(content);
            return new MarkCommand(index, false);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Input a number after the \"unmark\" keyword.");
        }
    }

    private static Command parseDelete(String content) throws ConnivingException {
        try {
            int index = Integer.parseInt(content);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Input a number after the \"unmark\" keyword.");
        }
    }

    private static Command parseTodo(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("Must have Task description after todo keyword");
        }

        return new AddCommand(new Todo(content));
    }

    private static Command parseDeadline(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("Must have Task description after deadline keyword");
        }
        try {
            String[] deadlineParams = content.trim().split("\\s*/by\\s*");
            Temporal byTime = DateParser.parse(deadlineParams[1]);
            Task deadline = new Deadline(deadlineParams[0], byTime);

            return new AddCommand(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConnivingException("Incorrect syntax");
        }
    }

    private static Command parseEvent(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("Must have Task description after event keyword");
        }

        try{
            String[] eventParams = content.trim().split("\\s*/from\\s*|\\s*/to\\s*");
            Temporal fromTime = DateParser.parse(eventParams[1]);
            Temporal toTime = DateParser.parse(eventParams[2]);

            Task event = new Event(eventParams[0], fromTime, toTime);
            return new AddCommand(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConnivingException("Incorrect syntax");
        }
    }
}
