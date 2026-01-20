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
        String[] content = input.trim().split(" ", 2);
        String firstWord = content[0];
        String parameters = (content.length > 1) ? content[1] : "";

        switch (firstWord) {
            case "bye":
                return false;
            case "list":
                System.out.println(formatOutput(taskList.toString()));
                break;
            case "mark":
                Task taskToMark = taskList.get(Integer.parseInt(parameters));
                taskToMark.markDone();

                System.out.println(formatOutput("I've marked this task as done: \n" + taskToMark));
                break;
            case "unmark":
                Task taskToUnmark = taskList.get(Integer.parseInt(parameters));
                taskToUnmark.unmarkDone();

                System.out.println(formatOutput("I've marked this task as done: \n" + taskToUnmark));
                break;
            default:
                taskList.add(input);
                System.out.println(formatOutput("added: " + input));
        }
        return true;
    }
}
