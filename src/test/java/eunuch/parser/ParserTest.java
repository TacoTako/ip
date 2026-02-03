package eunuch.parser;

import org.junit.jupiter.api.Test;

import eunuch.command.AddCommand;
import eunuch.command.Command;
import eunuch.command.ExitCommand;
import eunuch.command.PrintCommand;


public class ParserTest {
    @Test
    public void parseEmptyTest() {
        Command parsed = Parser.parse("");
        assert parsed instanceof PrintCommand;
    }

    @Test
    public void parseExitTest() {
        Command parsed = Parser.parse("bye");
        assert parsed instanceof ExitCommand;
    }

    @Test
    public void parseGarbageTest() {
        //should not add any task
        Command parsed = Parser.parse("garbage");
        assert parsed instanceof PrintCommand;
    }

    @Test
    public void parseEmptyTodoTest() {
        //should not add any task
        Command parsed = Parser.parse("todo");
        assert parsed instanceof PrintCommand;
    }

    @Test
    public void parseCorrectTodoTest() {
        //should add a task
        Command parsed = Parser.parse("todo example task");
        assert parsed instanceof AddCommand;
    }

    @Test
    public void parseMixedCapsTodoTest() {
        //should add a task
        Command parsed = Parser.parse("ToDo example task");
        assert parsed instanceof AddCommand;
    }

    @Test
    public void parseNoSpaceTodoTest() {
        //should not add a task
        Command parsed = Parser.parse("todoexample task");
        assert parsed instanceof PrintCommand;
    }
}
