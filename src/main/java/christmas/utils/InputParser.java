package christmas.utils;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParser {
    //문자열-숫자 형태의 패턴을 정의
    private static final Pattern VALID_PATTERN = Pattern.compile("^[가-힣]+-\\d+$");

    // 입력 문자열을 쉼표 기준으로 쪼갠 후 각 요소들이 패턴에 일치하는지를 확인 후
    // 패턴에 일치한다고 하면 "-"를 기준으로 음식이름, 주문갯수로 쪼개어 결과를 리스트에 담음
    public static List<String[]> parseInputToArray(String input) {
        return Stream.of(input.split(","))
                .map(String::trim)
                .filter(item -> VALID_PATTERN.matcher(item).matches())
                .map(item -> item.split("-"))
                .collect(Collectors.toList());
    }
}