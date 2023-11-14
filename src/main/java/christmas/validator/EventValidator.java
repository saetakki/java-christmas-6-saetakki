package christmas.validator;

import static christmas.validator.ValidatorTemplate.validateTemplate;

import christmas.Constants.ErrorMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class EventValidator {

    private static Predicate<String> hasUniqueItems = menuInput -> {
        Set<String> uniqueItems = new HashSet<>();
        return Arrays.stream(menuInput.split(","))
                .map(item -> item.split("-")[0].trim())
                .allMatch(uniqueItems::add);  // 중복이 없으면 true 반환
    };

    private static Predicate<String> withinMaxQuantity(int maxQuantity) {
        return menuInput -> Arrays.stream(menuInput.split(","))
                .mapToInt(item -> Integer.parseInt(item.split("-")[1].trim()))
                .sum() <= maxQuantity;
    };


    public static void validateUniqueItems(String menuInput) {
        validateTemplate(
                hasUniqueItems,
                menuInput,
                () -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage())
        );
    }

    public static void validateQuantityDoesNotExceed(String menuInput, int maxQuantity) {
        validateTemplate(
                withinMaxQuantity(maxQuantity),
                menuInput,
                () -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage())
        );
    }
}
