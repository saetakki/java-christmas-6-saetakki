package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputTemplates {



    @FunctionalInterface
    interface Validator {
        void validate(String input) throws IllegalArgumentException;
    }


    public static String promptAndGetInput(String message){
        System.out.println(message);
        return Console.readLine();
    }

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
