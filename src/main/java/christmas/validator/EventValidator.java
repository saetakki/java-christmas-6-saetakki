package christmas.validator;

import static christmas.validator.ValidatorTemplate.validateTemplate;

import christmas.Constants.ErrorMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class EventValidator {

    // 중복된 항목이 존재하는지 검사하는 Predicate
    // 입력받은 주문내역을 파싱하여 음식 이름만을 취한 후
    // 중복 여부의 존재에 따라서 중복이 없다면 true 있다면 false 반환
    private static Predicate<String> hasUniqueItems = menuInput -> {
        Set<String> uniqueItems = new HashSet<>();
        return Arrays.stream(menuInput.split(","))
                .map(item -> item.split("-")[0].trim())
                .allMatch(uniqueItems::add);  // 중복이 없으면 true 반환
    };

    // 최대수량을 초과하는지 검사하는 Predicate
    // 최대 수량을 입력받아 주어진 menuInput 문자열에서 숫자들을 취한 후 그 합이 maxQuantity를 초과하는지 여부를 판별
    // maxQuantity 이내라면 true/아니라면 false
    private static Predicate<String> withinMaxQuantity(int maxQuantity) {
        return menuInput -> Arrays.stream(menuInput.split(","))
                .mapToInt(item -> Integer.parseInt(item.split("-")[1].trim()))
                .sum() <= maxQuantity;
    };

    // menuInput이 유니크한 값들로만 이루어졌는지 테스트하는 메서드
    // menuInput을 입력받으면 hasUniqueItems 테스트를 실행
    // 오류 발생시 IllegalArgumentException 발생
    public static void validateUniqueItems(String menuInput) {
        validateTemplate(
                hasUniqueItems,
                menuInput,
                () -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage())
        );
    }

    // menuInput이 maxQuantity를 초과하는지를 판별하는 메서드
    // menuInput과 maxQuantity를 입력받으면 withInMaxQuantity 테스트를 실행
    // 오류 발생 시 IllegalArgumentException 발생
    public static boolean validateQuantityDoesNotExceed(String menuInput, int maxQuantity) {
        validateTemplate(
                withinMaxQuantity(maxQuantity),
                menuInput,
                () -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage())
        );
        return false;
    }
}
