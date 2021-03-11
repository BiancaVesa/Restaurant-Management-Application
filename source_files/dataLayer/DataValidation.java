package dataLayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    public static boolean isValidName(String string) {
        String reg = "^[\\p{L} -]+$";
        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static boolean isValidDate(String string) {
        String reg = "([0-9]{2})\\.([0-9]{2})\\.([0-9]{4})";
        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static boolean isValidNumber(String string) {
        String reg = "(?<=\\s|^)\\d+(?=\\s|$)";
        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static boolean isValidPrice(float price) {
        if (price > 0)
            return true;
        return false;
    }
}
