package eunuch.task;

import eunuch.ConnivingException;

import java.util.ArrayList;

/**
 * This class represents a list of tasks to be used by the chatbot
 */
public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<String> data) {
        list = new ArrayList<>();
        loadListFromData(data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (list.isEmpty()) {
            return "";
        }

        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }

        //remove trailing newline
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Returns size of list
     * @return number of tasks in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds a task to the list from the back
     *
     * @param task Task to be added to the list
     * @return Success message to be printed after adding Task
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Returns the task in the list with the index specified
     *
     * @param index index of item to get (starts from 1)
     * @return the Task specified
     * @throws ConnivingException if the index of the item deos not exist
     */
    public Task get(int index) throws ConnivingException {
        try {
            return this.list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ConnivingException("Item does not exist in list!");
        }
    }

    /**
     * Deletes the task in the list with the index specified
     *
     * @param index index of item to get (starts from 1)
     * @throws ConnivingException if the index of the item deos not exist
     */
    public void delete(int index) throws ConnivingException {
        try {
            this.list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ConnivingException("Item does not exist in list!");
        }
    }

    public String toData() {
        StringBuilder data = new StringBuilder();
        for (Task task : list) {
            data.append(task.toData() + "\n");
        }
        return data.toString();
    }

    private void loadListFromData(ArrayList<String> lines) {
        for (String line : lines) {
            String[] params = line.split(" ");
            boolean isMarkedDone = Boolean.parseBoolean(params[2]);

            Task taskToAdd;
            switch (params[0]) {
            case "T":
                taskToAdd = new Todo(params[1]);
                break;
            case "D":
                taskToAdd = new Deadline(params[1], params[3]);
                break;
            case "E":
                taskToAdd = new Event(params[1], params[3], params[4]);
                break;
            default:
                taskToAdd = new Todo("");
            }

            taskToAdd.mark(isMarkedDone);
            add(taskToAdd);
        }
    }
}
