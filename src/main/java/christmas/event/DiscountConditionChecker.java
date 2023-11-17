package christmas.event;

import static christmas.Constants.MINIMUM_SPEND_FOR_EVENT;
import static christmas.event.dateChecker.isWeekend;

import christmas.data.Foods.FoodItem;
import christmas.dto.OrderList;
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
    public static boolean isEligibleForDiscount(String menu, Integer date) {
        FoodItem foodItem = FoodItem.valueOf(menu);
        return (isMainCourse.test(foodItem) && isWeekend(date)) || (isDessert.test(foodItem) && !isWeekend(date));
    }

    // 이벤트 자체에 참가 가능한지를 판단하는 함수
    // 음료만 구매하거나 이벤트 참여에 필요한 최소 비용을 맞추지 못하면 false 리턴
    public static boolean isEligibleForEvent(Integer originalTotalCost, OrderList orderList){
        return originalTotalCost >= MINIMUM_SPEND_FOR_EVENT && !orderList.isBeverageOnly();
    }


}

