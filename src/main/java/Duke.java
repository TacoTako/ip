public class Duke {
    public static void main(String[] args) {
        printLine();
        greet();
        printLine();
        sayGoodbye();
        printLine();
    }

    private static void greet() {
        String greeting =  "Hello! I'm Conniving Eunuch, the Duplicitous\nWhat can I do for you?";
        System.out.println(greeting);
    }

    private static void sayGoodbye() {
        String bye =  "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
