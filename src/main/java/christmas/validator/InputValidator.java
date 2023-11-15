package christmas.validator;

import static christmas.data.Foods.FoodItem.isExistMenu;
import static christmas.validator.ValidatorTemplate.validateTemplate;

import christmas.Constants.ErrorMessage;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class InputValidator {



    // 입력값이 양의 정수인지 판별하는 Predicate
    // input값을 입력받으면 해당 입력값을 Integer로 변환했을 시 0보다 큰 정수인지를 판별하고 결과값 리턴
    // Integer로 변환이 안된다면 NumberFormatException 발생
    private static final Predicate<String> isPositiveInteger = input ->{
        try{
            return Integer.parseInt(input)>0;
        } catch (NumberFormatException e){
            throw new NumberFormatException(ErrorMessage.INVALID_DATE.getMessage());
        }
    };

    // 입력값이 범위 안에 위치하는지 판별하는 Predicate
    // 범위의 최소값과 최대값을 입력받으면 input값이 해당 범위 안에 존재하는 정수인지 파악
    // 만약 input값이 정수로 변환 가능하며 범위 내에 있으면 true 그렇지 않다면 false를 리턴하지만
    // Integer로 형변환 자체가 불가하다면 NumberFormatException 발생
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


    // 입력값이 메뉴입력 방식대로 입력되었는지 판단하는 Predicate
    // 문자열-숫자 형태에 값이 추가된다면,로 구분되어있는지를 정규표현식으로 검사
    // 입력형식과 일치한다면 true 아니라면 false 리턴
    public static final Predicate<String> isValidMenuInputForm =
            menuInput -> Pattern.compile("^\\s*[가-힣\\s]+-\\d+(\\s*,\\s*[가-힣\\s]+-\\d+)*\\s*$").matcher(menuInput).matches();


    // 입력한 메뉴가 실제로 판매중인 메뉴인지 판별하는 Predicate
    // 입력값을 isExistMenu 메서드를 통해 존재하는지 검사하고 존재하면 true, 아니라면 false 리턴
    public static final Predicate<String> isMenuAvailable =
            menu -> isExistMenu(menu);


    // 주문 가능한 메뉴인지 판별하는 메서드
    // input값을 isMenuAvailable 테스트에 넣어 검증
    // 에러 발생시 NoSuchElementException 에러 발생
    public static boolean validateMenuAvailable(String input){
        validateTemplate(
                isMenuAvailable,
                input,
                () -> new NoSuchElementException(ErrorMessage.INVALID_ORDER.getMessage())
        );
        return true;
    }

    // 메뉴입력이 정해진 형식대로 이루어졌는지 검사하는 메서드
    // menuInput값을 isValidMenuInputForm 테스트로 검사
    // 실패 시 IllegalArgumentException 발생
    public static void validateMenuInputForm(String menuInput){
        validateTemplate(
                isValidMenuInputForm,
                menuInput,
                () -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage())
        );
    }

    // 유효한 범위 내의 정수 입력값인지를 테스트하는 메서드
    // 예약일을 입력하라는 메세지 출력 이후 입력받은 값을 우선 isPositiveInteger 테스트 시행
    // 테스트 통과 시 withRange 테스트를 다시 실행하여 통과되는지 확인
    // 실패한다면 IllegalArgumentException 반환

    public static boolean validateWithinRange(String input, int lowerBound, int upperBound) {
        System.out.println(input);
        isPositiveInteger.test(input);
        Predicate<String> withinRange = isWithinRange(lowerBound, upperBound);
        validateTemplate(
                withinRange,
                input,
                () -> new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage())
        );
        return true;
    }
}