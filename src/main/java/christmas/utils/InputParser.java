package christmas.utils;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParser {
    private static final Pattern VALID_PATTERN = Pattern.compile("^[가-힣]+-\\d+$");

    public static List<String[]> parseInputToArray(String input) {
        return Stream.of(input.split(","))
                .map(String::trim)
                .filter(item -> VALID_PATTERN.matcher(item).matches())
                .map(item -> item.split("-"))
                .collect(Collectors.toList());
    }
}