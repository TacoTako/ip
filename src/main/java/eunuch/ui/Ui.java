package eunuch.ui;

import java.io.IOException;

public class Ui {
    public Ui () {

    }

    public void greet() {
        String greeting =  "Your Majesty, the officials are not to be trusted.\n" +
                "Only I, Conniving Eunuch, truly cares for your well-being.\n" +
                "How may this lowly one assist you?";
        displayText(greeting);
    }

    public void sayGoodbye() {
        String bye =  "As you wish, Your Majesty. Your servant humbly withdraws.";
        displayText(bye);
    }

    public void displayText(String s) {
        System.out.println(formatOutput(s));
    }

    private static String formatOutput(String s) {
        final String LINE = "____________________________________________________________";
        String output = LINE + "\n" + s + "\n" + LINE;
        return output.indent(4);
    }
}
