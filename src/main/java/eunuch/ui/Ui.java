package eunuch.ui;

/**
 * Represent the interface that will communicat the action of the code to the user
 */
public class Ui {
    private final MainWindow window;

    public Ui(MainWindow window) {
        this.window = window;
    }

    /**
     * Prints a greeting to the user, called on startup
     */
    public void greet() {
        String greeting = "Your Majesty, the officials are not to be trusted.\n"
                + "Only I, Conniving Eunuch, truly cares for your well-being.\n"
                        + "How may this lowly one assist you?";
        displayText(greeting);
    }

    /**
     * Prints the exit message to the user
     */
    public void sayGoodbye() {
        String bye = "As you wish, Your Majesty. Your servant humbly withdraws.";
        displayText(bye);
    }

    /**
     * Prints the message to the user after formatting it
     * @param s message to display
     */
    public void displayText(String s) {
        assert window != null;
        window.printEunuchMsg(s);
    }

    private static String formatOutput(String s) {
        final String line = "________________________________________________________";
        String output = line + "\n" + s + "\n" + line;
        return output.indent(4);
    }
}
