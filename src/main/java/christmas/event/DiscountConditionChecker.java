package christmas.event;

import static christmas.event.dateChecker.isWeekend;

import christmas.data.Foods.FoodItem;
import java.util.function.Predicate;

public class DiscountConditionChecker {
    // isMainCourse.test(food) 를 통해 food가 메인 요리에 속하는지 검증하는 인터페이스
    private static final Predicate<FoodItem> isMainCourse =
            food -> "MainCourse".equals(food.getType());

    // isDessert.test(food) 를 통해 food가 디저트에 속하는지 검증하는 인터페이스
    private static final Predicate<FoodItem> isDessert =
            food -> "Dessert".equals(food.getType());

    // 메뉴 이름과 날짜를 인자로 받아 해당 품목의 할인 적용 여부를 판단하는 함수
    // 주말 - 메인코스 | 평일 - 디저트에 할인 적용
    // 메뉴 항목이 존재하지 않는 경우 예외처리
    public static boolean isEligibleForDiscount(String menu, Integer date) {
        try {
            FoodItem foodItem = FoodItem.valueOf(menu);
            return (isMainCourse.test(foodItem) && isWeekend(date)) || (isDessert.test(foodItem) && !isWeekend(date));

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("에러발생");
        }
    }
}

