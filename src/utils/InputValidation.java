package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputValidation {

    private static final String INPUT_NUMBER_DIGIT = "[0-9]+";

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static Integer inputNumber(final String msgErr) {
        final Scanner sc = new Scanner(System.in);
        String value = String.valueOf(sc.nextLine());
        while (!value.matches(INPUT_NUMBER_DIGIT)) {
            System.out.println(msgErr);
            value = String.valueOf(sc.nextLine());
        }

        return Integer.parseInt(value);
    }

    public static String inputString(final String regex, final String msgErr) {
        final Scanner sc = new Scanner(System.in);
        String value = String.valueOf(sc.nextLine());
        while (!value.matches(regex)) {
            System.out.println(msgErr);
            value = String.valueOf(sc.nextLine());
        }

        return value;
    }

    public static Date inputDate(final String msgErr) throws ParseException {
        final Scanner sc = new Scanner(System.in);
        String value = String.valueOf(sc.nextLine());
        while (isInvalidParse(value)) {
            System.out.println(msgErr);
            value = String.valueOf(sc.nextLine());
        }

        return new SimpleDateFormat(DATE_FORMAT).parse(value);
    }

    private static boolean isInvalidParse(String dateStr) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return true;
        }
        return false;
    }
}
