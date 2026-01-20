import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> list;

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

    public void add(String s) {
        this.list.add(s);
    }

    public void delete(String s) {

    }
}
