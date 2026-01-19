import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        greet();

        //while loop for listening to users
        while (true) {
            String input = s.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(formatOutput(input));
            }
        }

        sayGoodbye();
    }

    private static void greet() {
        String greeting =  "Hello! I'm Conniving Eunuch, the Duplicitous\nWhat can I do for you?";
        System.out.println(formatOutput(greeting));
    }

    private static void sayGoodbye() {
        String bye =  "Bye. Hope to see you again soon!";
        System.out.println(formatOutput(bye));
    }

    private static String formatOutput(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" +line;
    }
}
