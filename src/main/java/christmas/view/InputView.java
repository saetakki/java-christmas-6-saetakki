package christmas.view;

import static christmas.validator.EventValidator.validateQuantityDoesNotExceed;
import static christmas.validator.EventValidator.validateUniqueItems;
import static christmas.validator.InputValidator.validateMenuInputForm;
import static christmas.validator.InputValidator.validateSeperatedByComma;
import static christmas.validator.InputValidator.validateWithinRange;
import static christmas.view.InputPrompt.promptAndGetInput;

import christmas.Constants.ErrorMessage;

public class InputView {

    public static int inputVisitDay() {
        while (true) {
            String dateInput = promptAndGetInput("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            try {
                validateWithinRange(dateInput,1,31);
                return Integer.parseInt(dateInput);
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.INVALID_DATE.getMessage());
            }
        }
    }


        public static String inputMenu() {
            while (true) {
                String menuInput = promptAndGetInput("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                try {
                    validateMenuInputForm(menuInput);
                    validateSeperatedByComma(menuInput);
                    validateUniqueItems(menuInput);
                    validateQuantityDoesNotExceed(menuInput, 20);
                    return menuInput;
                } catch (IllegalArgumentException e) {
                    System.out.println(ErrorMessage.INVALID_ORDER.getMessage());
                }
            }
        }

}
