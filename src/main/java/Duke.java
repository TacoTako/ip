import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        TaskList taskList = new TaskList();
        boolean endConversation = false;
        greet();

        //while loop for listening to users
        while (!endConversation) {
            String input = s.nextLine();
            String keyWord = getFirstWord(input);
            switch (input) {
                case "bye":
                    endConversation = true;
                    break;
                case "list":
                    System.out.println(formatOutput(taskList.toString()));
                    break;
                default:
                    taskList.add(input);
                    System.out.println(formatOutput("added: " + input));
            }
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
}
