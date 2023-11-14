//package christmas.view;
//
//import static christmas.data.Foods.FoodItem.isExistMenu;
//
//import camp.nextstep.edu.missionutils.Console;
//import christmas.Constants.ErrorMessage;
//import christmas.Constants.InputMessage;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.regex.Pattern;
//
//public class SimpleInput {
//
//    public static boolean isValidDate(String input) {
//        try {
//            int tmp = Integer.parseInt(input);
//            return (tmp >= 1 && tmp <= 31);
//        } catch (NumberFormatException e) {
//            System.out.println(ErrorMessage.INVALID_DATE.getMessage());
//        }
//        return false;
//    }
//
//    ;
//
//    public static boolean isValidForm(String input) {
//        return Pattern.compile("^\\s*[가-힣\\s]+-\\d+(\\s*,\\s*[가-힣\\s]+-\\d+)*\\s*$").matcher(input).matches();
//    }
//
//    public static boolean isUnique(String input) {
//        return isExistMenu(input);
//    }
//
//    private static boolean withinMaximumQuantity(String input) {
//        return Arrays.stream(input.split(","))
//                .mapToInt(item -> Integer.parseInt(item.split("-")[1].trim()))
//                .sum() <= 20;
//    }
//
//    public static boolean hasUniqueItems(String input) {
//        Set<String> uniqueItems = new HashSet<>();
//        return Arrays.stream(input.split(","))
//                .map(item -> item.split("-")[0].trim())
//                .allMatch(uniqueItems::add);  // 중복이 없으면 true 반환
//    }
//
//    ;
//
//
//    public static Integer simpleInput() {
//        while (true) {
//            System.out.println(InputMessage.INPUT_DATE.getMessage());
//            String input = Console.readLine();
//            try {
//                if (isValidDate(input)) {
//                    return Integer.parseInt(input);
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println(ErrorMessage.INVALID_DATE.getMessage());
//            }
//        }
//    }
//
//    public static String simpleMenuInput() {
//        while (true) {
//            System.out.println(InputMessage.INPUT_MENU.getMessage());
//            String input = Console.readLine();
//            try {
//                if (withinMaximumQuantity(input) && hasUniqueItems(input)) {
//                    return input;
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println(ErrorMessage.INVALID_ORDER.getMessage());
//            }
//        }
//    }
//};
//
//
//
