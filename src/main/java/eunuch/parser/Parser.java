package eunuch.parser;

import java.time.temporal.Temporal;

import eunuch.command.AddCommand;
import eunuch.command.Command;
import eunuch.command.DeleteCommand;
import eunuch.command.ExitCommand;
import eunuch.command.ListCommand;
import eunuch.command.MarkCommand;
import eunuch.command.PrintCommand;
import eunuch.command.SearchCommand;
import eunuch.exception.ConnivingException;
import eunuch.task.Deadline;
import eunuch.task.Event;
import eunuch.task.Task;
import eunuch.task.Todo;

/**
 * Represents a parser that can read user commands to return Command object representations
 */
public class Parser {
    /**
     * Parses a user input to get the keywords to determine behaviour
     * @param input String input from user keyboard
     * @return a Command that represents the user's input
     */
    public static Command parse(String input) {
        String[] parts = input.trim().split(" ", 2);
        String keyword = parts[0].toLowerCase();
        String content = (parts.length > 1) ? parts[1] : "";

        try {
            return switch (keyword) {
            case "" -> new PrintCommand("I must have not caught that, Your Highness.");
            case "help" -> parseHelp();
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> parseMark(content);
            case "unmark" -> parseUnmark(content);
            case "todo" -> parseTodo(content);
            case "deadline" -> parseDeadline(content);
            case "event" -> parseEvent(content);
            case "delete" -> parseDelete(content);
            case "find" -> parseFind(content);
            default -> new PrintCommand("Your foolish servant does not understand.");
            };
        } catch (ConnivingException e) {
            return new PrintCommand(e.getMessage());
        }
    }

    private static Command parseFind(String content) {
        if (content.isEmpty()) {
            throw new ConnivingException("What would you like to search for my lord?");
        }
        return new SearchCommand(content, null);
    }

    private static Command parseHelp() {
        String helpString = "At once, my lord. Please instruct me as such:\n-todo <desc>\n-deadline <desc> /by <date>"
                + "\n-event <desc> /from <start> /to <end>\n-list\n-mark/unmark/delete <number>\n-find <keyword>\nbye"
                + "\n Date-time can be read in format: dd/MM/YYYY HHmm with optional time and 2 digit year allowed";
        return new PrintCommand(helpString);
    }

    private static Command parseMark(String content) throws ConnivingException {
        try {
            int index = Integer.parseInt(content);
            return new MarkCommand(index, true);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Please input a number after the \"mark\" keyword.");
        }
    }

    private static Command parseUnmark(String content) throws ConnivingException {
        try {
            int index = Integer.parseInt(content);
            return new MarkCommand(index, false);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Please input a number after the \"unmark\" keyword.");
        }
    }

    private static Command parseDelete(String content) throws ConnivingException {
        try {
            int index = Integer.parseInt(content);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Please input a number after the \"delete\" keyword.");
        }
    }

    private static Command parseTodo(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("What is the task should I remind you of, Your Highness?");
        }

        return new AddCommand(new Todo(content));
    }

    private static Command parseDeadline(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("What is the task should I remind you of, Your Highness?");
        }
        try {
            String[] deadlineParams = content.trim().split("\\s*/by\\s*");
            Temporal byTime = DateParser.parse(deadlineParams[1]);
            Task deadline = new Deadline(deadlineParams[0], byTime);

            return new AddCommand(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConnivingException("I am sorry, this commoner must not understand your terms.");
        }
    }

    private static Command parseEvent(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("What is the task should I remind you of, Your Highness?");
        }

        try {
            String[] eventParams = content.trim().split("\\s*/from\\s*|\\s*/to\\s*");
            Temporal fromTime = DateParser.parse(eventParams[1]);
            Temporal toTime = DateParser.parse(eventParams[2]);

            Task event = new Event(eventParams[0], fromTime, toTime);
            return new AddCommand(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConnivingException("I am sorry, this commoner must not understand your terms.");
        }
    }
}
