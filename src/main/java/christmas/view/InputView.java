package christmas.view;

import static christmas.validator.EventValidator.validateQuantityDoesNotExceed;
import static christmas.validator.EventValidator.validateUniqueItems;
import static christmas.validator.InputValidator.validateMenuInputForm;
import static christmas.validator.InputValidator.validateWithinRange;
import static christmas.view.InputTemplates.inputWithValidationsReturnInteger;
import static christmas.view.InputTemplates.inputWithValidationsReturnString;

import christmas.Constants.InputMessage;

public class InputView {

    public static int inputVisitDay() {
        return inputWithValidationsReturnInteger(
                InputMessage.INPUT_DATE.getMessage(),
                input -> validateWithinRange(input,1,31)
        );
    }


    public static String inputMenu(){
        return inputWithValidationsReturnString(InputMessage.INPUT_MENU.getMessage(),
            input -> {
                validateMenuInputForm(input);
                validateUniqueItems(input);
                validateQuantityDoesNotExceed(input, 20);
            }
        );
    }



}
