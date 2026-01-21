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

    //returns true if continuing conversation, false if ending
    private static boolean processInput(String input) {
        String[] temp = input.trim().split(" ", 2);
        String keyword = temp[0];
        String content = (temp.length > 1) ? temp[1] : "";

        try {
            switch (keyword) {
                case "":
                    System.out.println(formatOutput("Say something"));
                    break;
                case "bye":
                    return false;
                case "list":
                    System.out.println(formatOutput(taskList.toString()));
                    break;
                case "mark":
                    parseMark(content);
                    break;
                case "unmark":
                    parseUnmark(content);
                    break;
                case "todo":
                    parseTodo(content);
                    break;
                case "deadline":
                    parseDeadline(content);
                    break;
                case "event":
                    parseEvent(content);
                    break;
                default:
                    System.out.println(formatOutput("I don't know what you mean."));
            }
        } catch (ConnivingException e) {
            System.out.println(formatOutput(e.getMessage()));
        }

        return true;
    }

    private static void parseMark(String content) throws ConnivingException {
        try {
            Task task = taskList.get(Integer.parseInt(content));
            task.markDone();
            System.out.println(formatOutput("I've marked this task as done: \n" + task));
        } catch (NumberFormatException e) {
            throw new ConnivingException("Input a number after the \"mark\" keyword.");
        } catch (IndexOutOfBoundsException e) {
            throw new ConnivingException("Item does not exist in list!");
        }
    }

    private static void parseUnmark(String content) throws ConnivingException {
        try {
            Task task = taskList.get(Integer.parseInt(content));
            task.unmarkDone();
            System.out.println(formatOutput("I've unmarked this task as done: \n" + task));
        } catch (NumberFormatException e) {
            throw new ConnivingException("Input a number after the \"unmark\" keyword.");
        } catch (IndexOutOfBoundsException e) {
            throw new ConnivingException("Item does not exist in list!");
        }
    }

    private static void parseTodo(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("Must have Task description after todo keyword");
        }
        Task todo = new Todo(content);
        System.out.println(formatOutput(taskList.add(todo)));
    }

    private static void parseDeadline(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("Must have Task description after deadline keyword");
        }
        try {
            String[] deadlineParams = content.trim().split("\\s*/by\\s*");
            Task deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
            System.out.println(formatOutput(taskList.add(deadline)));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConnivingException("Incorrect syntax");
        }
    }

    private static void parseEvent(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("Must have Task description after event keyword");
        }

        try{
            String[] eventParams = content.trim().split("\\s*/from\\s*|\\s*/to\\s*");
            Task event = new Event(eventParams[0], eventParams[1], eventParams[2]);
            System.out.println(formatOutput(taskList.add(event)));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConnivingException("Incorrect syntax");
        }
    }

}
