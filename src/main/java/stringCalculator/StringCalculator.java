package stringCalculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private StringCalculator() {
    }

    public static int splitAndSum(String text) throws RuntimeException {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        int[] splitAndParseInt = splitAndParseInt(text);
        validatePositive(splitAndParseInt);
        return sum(splitAndParseInt);
    }

    private static int[] splitAndParseInt(String text) {
        return parseInt(split(text));
    }

    private static String[] split(String text) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        if (m.find()) {
            String customDelimiter = m.group(1);
            return m.group(2).split(customDelimiter);
        }
        return text.split("[,:]");
    }

    private static int[] parseInt(String[] token) {
        int[] parsedToInt = new int[token.length];
        for (int i = 0; i < token.length; i++) {
            parsedToInt[i] = Integer.parseInt(token[i]);
        }
        return parsedToInt;
    }

    private static void validatePositive(int[] numbers) throws RuntimeException {
        for (int number : numbers) {
            throwErrorIfNegative(number);
        }
    }

    private static void throwErrorIfNegative(int number) throws RuntimeException {
        if (number < 0) {
            throw new RuntimeException();
        }
    }

    private static int sum(int[] numbers) {
        return Arrays.stream(numbers).reduce(0, Integer::sum);
    }
}
