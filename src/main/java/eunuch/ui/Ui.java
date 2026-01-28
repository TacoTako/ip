package eunuch.ui;

public class Ui {
    public Ui () {

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
