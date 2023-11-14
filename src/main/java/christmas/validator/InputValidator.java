package christmas.validator;

import static christmas.data.Foods.FoodItem.isExistMenu;
import static christmas.validator.ValidatorTemplate.validateTemplate;

import christmas.Constants.ErrorMessage;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class InputValidator {



    private static final Predicate<String> isPositiveInteger = input ->{
        try{
            return Integer.parseInt(input)>0;
        } catch (NumberFormatException e){
            throw new NumberFormatException(ErrorMessage.INVALID_DATE.getMessage());
        }
    };

    public static final Predicate<String> isWithinRange(int lowerBound, int upperBound) {
        return input -> {
            try {
                int number = Integer.parseInt(input);
                return number >= lowerBound && number <= upperBound;
            } catch (NumberFormatException e) {
                return false;
            }
        };
    };

    public static final Predicate<String> isValidMenuInputForm =
            menuInput -> Pattern.compile("^\\s*[가-힣\\s]+-\\d+(\\s*,\\s*[가-힣\\s]+-\\d+)*\\s*$").matcher(menuInput).matches();

    public static final Predicate<String> isMenuAvailable =
            menu -> isExistMenu(menu);


    public static boolean validateMenuAvailable(String input){
        validateTemplate(
                isMenuAvailable,
                input,
                () -> new NoSuchElementException(ErrorMessage.INVALID_ORDER.getMessage())
        );
        return true;
    }

    public static void validateMenuInputForm(String menuInput){
        validateTemplate(
                isValidMenuInputForm,
                menuInput,
                () -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage())
        );
    }

    public static boolean validateWithinRange(String input, int lowerBound, int upperBound) {
        isPositiveInteger.test(input);
        Predicate<String> withinRange = isWithinRange(lowerBound, upperBound);
        validateTemplate(
                withinRange,
                input,
                () -> new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage())
        );
        return true;
    }

    public static void validatePositiveInteger(String input) {
        validateTemplate(
                isPositiveInteger,
                input,
                () -> new IllegalArgumentException("입력값은 양의 정수여야 합니다.")
        );
    }


}