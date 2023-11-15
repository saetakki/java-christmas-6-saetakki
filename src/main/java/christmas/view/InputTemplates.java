package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputTemplates {



    //문자열 입력에 대한 유효성 검사를 정의하는 함수형 인터페이스 Validator
    @FunctionalInterface
    interface Validator {
        void validate(String input) throws IllegalArgumentException;
    }


    // 유저로부터 입력값을 받는 메서드
    // 출력과 입력을 하나의 메서드로 통합
    public static String promptAndGetInput(String message){
        System.out.println(message);
        return Console.readLine();
    }

    // 결과값으로 Integer를 리턴하는 input validator
    // 에러메세지와 validator를 인자로 받아
    // 입력값을 통과할 때까지 while문을 통해 사용자에게 값을 입력받고
    // 유효한 입력이 들어왔다면 input에 대한 validation 진행 후
    // 정수값 리턴
    public static int inputWithValidationsReturnInteger(String promptMessage, Validator validator){
        while (true) {
            String input = promptAndGetInput(promptMessage);
            try {
                validator.validate(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 결과값으로 String을 리턴하는 input validator
    // 에러메세지와 테스트를 인자로 입력받은 후
    // 입력값을 통과할 때까지 while문을 통해 사용자에게 값을 입력받고
    // 유효한 입력이 들어왔다면 input에 대한 validation 진행 후
    // 문자열 리턴
    public static String inputWithValidationsReturnString(String promptMessage, Validator validator){
        while (true) {
            String input = promptAndGetInput(promptMessage);
            try {
                validator.validate(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}