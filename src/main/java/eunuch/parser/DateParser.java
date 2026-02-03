package eunuch.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Locale;

import eunuch.exception.ConnivingException;

/**
 * Represents a static class that can convert between Strings and LocalDate/LocalDateTime
 */
public class DateParser {

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm")
    );

    private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    );

    /**
     * Reads a string and converts it to a Date or DateTime object
     * @param input String to be parsed into a date
     * @return either a LocalDate or LocalDateTime representation of the input
     * @throws ConnivingException if the string cannot fit into any DateTimeFormat
     */
    public static Temporal parse(String input) throws ConnivingException {
        input = input.trim();
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                //ignore
            }
        }

        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
        throw new ConnivingException("Not a valid date/time: " + input);
    }

    /**
     * Converts a Temporal object back into a string to be stored on in data files
     * @param date Temporal object to be converted
     * @return a string representation of date/datetime
     */
    public static String dateToParsableString(Temporal date) {
        if (date instanceof LocalDateTime dt) {
            return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm"));
        }

        if (date instanceof LocalDate d) {
            return d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return "";
    }

    /**
     * Converts a Temporal object back into a formatted string to be printed out
     * @param date Temporal object to be converted
     * @return a string representation of date/datetime
     */
    public static String dateToString(Temporal date) {
        if (date instanceof LocalDateTime dt) {
            return dt.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH));
        }

        if (date instanceof LocalDate d) {
            return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        }

        return "";
    }
}
