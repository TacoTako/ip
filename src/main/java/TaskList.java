import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
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

    public String add(Task task) {
        this.list.add(task);
        return "Got it. I've added this task:\n" + task +
                "\nThere are " + list.size() + " tasks in your list now.";
    }

    public Task get(int index) throws ConnivingException {
        try {
            return this.list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ConnivingException("Item does not exist in list!");
        }
    }

    public void delete(int index) {
        try {
            this.list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ConnivingException("Item does not exist in list!");
        }
    }

    public String verboseDelete(int index) throws ConnivingException {
        Task task = get(index);
        delete(index);
        return "Got it. I've deleted this task:\n" + task +
                "\nThere are " + list.size() + " tasks in your list now.";
    }
}
