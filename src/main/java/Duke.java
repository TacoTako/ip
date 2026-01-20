import java.util.Scanner;

public class Duke {
    public static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean endConversation = false;
        greet();

        //while loop for listening to users
        while (!endConversation) {
            String input = s.nextLine();
            endConversation = !processInput(input);
        }

        sayGoodbye();
    }

    private static void greet() {
        String greeting =  "Your Majesty, the officials are not to be trusted.\nOnly I, Conniving Eunuch, truly cares for your well-being.\nHow may this lowly one assist you?";
        System.out.println(formatOutput(greeting));
    }

    private static void sayGoodbye() {
        String bye =  "As you wish, Your Majesty. Your servant humbly withdraws.";
        System.out.println(formatOutput(bye));
    }

    private static String formatOutput(String s) {
        String line = "____________________________________________________________";
        String output = line + "\n" + s + "\n" +line;
        return output.indent(4);
    }

    private static String getFirstWord(String s) {
        String[] words = s.trim().split("\\s+");
        return words[0].toLowerCase();
    }

    //returns true if continuing conversation, false if ending
    private static boolean processInput(String input) {
        String[] temp = input.trim().split(" ", 2);
        String firstWord = temp[0];
        String content = (temp.length > 1) ? temp[1] : "";

        switch (firstWord) {
            case "bye":
                return false;
            case "list":
                System.out.println(formatOutput(taskList.toString()));
                break;
            case "mark":
                Task taskToMark = taskList.get(Integer.parseInt(content));
                taskToMark.markDone();

                System.out.println(formatOutput("I've marked this task as done: \n" + taskToMark));
                break;
            case "unmark":
                Task taskToUnmark = taskList.get(Integer.parseInt(content));
                taskToUnmark.unmarkDone();

                System.out.println(formatOutput("I've marked this task as undone: \n" + taskToUnmark));
                break;
            case "todo":
                Task todo = new Todo(content);
                System.out.println(formatOutput(taskList.add(todo)));
                break;
            case "deadline":
                String[] deadlineParams = content.trim().split("\\s*/by\\s*");
                Task deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
                System.out.println(formatOutput(taskList.add(deadline)));
                break;
            case "event":
                String[] eventParams = content.trim().split("\\s*/from\\s*|\\s*/to\\s*");
                Task event = new Event(eventParams[0], eventParams[1], eventParams[2]);
                System.out.println(formatOutput(taskList.add(event)));
                break;
            default:
                System.out.println(formatOutput("I don't know what you mean."));
        }
        return true;
    }
}
