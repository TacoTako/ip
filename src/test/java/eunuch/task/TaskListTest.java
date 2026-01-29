package eunuch.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void createTaskListTest() {
        TaskList list = new TaskList();
        assertEquals(0, list.size());
    }

    @Test
    public void emptyTaskListStringTest() {
        TaskList list = new TaskList();
        assertEquals("", list.toString());
    }

    @Test
    public void taskListStringTest() {
        TaskList list = new TaskList();
        list.add(new TaskStub("test"));
        assertEquals("1. [ ] test", list.toString());
    }

    @Test
    public void addOneTaskTest() {
        TaskList list = new TaskList();
        list.add(new TaskStub("test"));
        assertEquals(1, list.size());
    }

    @Test
    public void addManyTasksTest() {
        TaskList list = new TaskList();
        list.add(new TaskStub("test"));
        list.add(new TaskStub("test"));
        list.add(new TaskStub("test"));
        list.add(new TaskStub("test"));
        list.add(new TaskStub("test"));
        assertEquals(5, list.size());
    }
}
