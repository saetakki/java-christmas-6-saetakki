package christmas.view;

import static christmas.validator.EventValidator.validateQuantityDoesNotExceed;
import static christmas.validator.EventValidator.validateUniqueItems;
import static christmas.validator.InputValidator.validateMenuInputForm;
import static christmas.validator.InputValidator.validateWithinRange;
import static christmas.view.InputTemplates.inputWithValidationsReturnInteger;
import static christmas.view.InputTemplates.inputWithValidationsReturnString;

import christmas.Constants.InputMessage;

public class InputView {

    // 예약일을 입력받는 메서드
    // inputWithValidationsReturnInteger 라는 입력 모듈을 활용
    // 입력값이 올바르지 않을 때 보여줄 에러 메세지와 input을 검증할 validateWithinRange 검증 메서드를 전달

    public static int inputVisitDay() {
        return inputWithValidationsReturnInteger(
                InputMessage.INPUT_DATE.getMessage(),
                input -> validateWithinRange(input,1,31)
        );
    }


    // 주문 메뉴를 입력받는 메서드
    // inputWithValidationsReturnString 라는 입력 모듈을 활용
    // 입력값이 올바르지 않을 때 보여줄 에러 메세지와 input을 검증할
    // 3개의 validation을 전달받음

    public static String inputOrderMenu(){
        return inputWithValidationsReturnString(InputMessage.INPUT_MENU.getMessage(),
            input -> {
                System.out.println(input);
                validateMenuInputForm(input);
                validateUniqueItems(input);
                validateQuantityDoesNotExceed(input, 20);
            }
        );
    }



}
