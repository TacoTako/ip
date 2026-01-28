import java.io.IOException;
import java.util.Scanner;

public class ConnivingEunuch {
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage("data/eunuch.txt");
    private static Ui ui = new Ui();

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
        taskList = storage.loadListFromFile();
        String greeting =  "Your Majesty, the officials are not to be trusted.\n" +
                "Only I, Conniving Eunuch, truly cares for your well-being.\n" +
                        "How may this lowly one assist you?";
        ui.displayText(greeting);
    }

    private static void sayGoodbye() {
        try{
            storage.writeListToFile(taskList);
        } catch (IOException e) {
            ui.displayText("Error in saving list");
        }
        String bye =  "As you wish, Your Majesty. Your servant humbly withdraws.";
        ui.displayText(bye);
    }

    //returns true if continuing conversation, false if ending
    private static boolean processInput(String input) {
        String[] temp = input.trim().split(" ", 2);
        String keyword = temp[0].toLowerCase();
        String content = (temp.length > 1) ? temp[1] : "";

        try {
            switch (keyword) {
            case "":
                ui.displayText("I must have not caught that, Your Highness.");
                break;
            case "bye":
                return false;
            case "list":
                ui.displayText(taskList.toString());
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
            case "delete":
                parseDelete(content);
                break;
            default:
                ui.displayText("Your foolish servant does not understand.");
            }
        } catch (ConnivingException e) {
            ui.displayText(e.getMessage());
        }

        return true;
    }

    private static void parseMark(String content) throws ConnivingException {
        try {
            Task task = taskList.get(Integer.parseInt(content));
            task.markDone();
            ui.displayText("I've marked this task as done: \n" + task);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Input a number after the \"mark\" keyword.");
        }
    }

    private static void parseUnmark(String content) throws ConnivingException {
        try {
            Task task = taskList.get(Integer.parseInt(content));
            task.unmarkDone();
            ui.displayText("I've unmarked this task as done: \n" + task);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Input a number after the \"unmark\" keyword.");
        }
    }

    private static void parseDelete(String content) throws ConnivingException {
        try {
            String output = taskList.verboseDelete(Integer.parseInt(content));
            ui.displayText(output);
        } catch (NumberFormatException e) {
            throw new ConnivingException("Input a number after the \"unmark\" keyword.");
        }
    }

    private static void parseTodo(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("Must have Task description after todo keyword");
        }
        Task todo = new Todo(content);
        ui.displayText(taskList.add(todo));
    }

    private static void parseDeadline(String content) throws ConnivingException {
        if (content.isEmpty()) {
            throw new ConnivingException("Must have Task description after deadline keyword");
        }
        try {
            String[] deadlineParams = content.trim().split("\\s*/by\\s*");
            Task deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
            ui.displayText(taskList.add(deadline));
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
            ui.displayText(taskList.add(event));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConnivingException("Incorrect syntax");
        }
    }

}
