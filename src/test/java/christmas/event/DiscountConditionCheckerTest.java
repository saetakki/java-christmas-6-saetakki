package christmas.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.dto.OrderList;
import org.junit.jupiter.api.Test;

public class DiscountConditionCheckerTest {

    // isEligibleForDiscount 메서드 테스트: 메인코스와 디저트 할인 조건 검증
    @Test
    public void 주말_및_평일_이벤트할인_테스트() {
        System.out.println(DiscountConditionChecker.isEligibleForDiscount("초코케이크",3));
        // 메인코스가 주말에 할인되는지 테스트
        assertTrue(!DiscountConditionChecker.isEligibleForDiscount("티본스테이크", 4)); // 월요일
        assertTrue(DiscountConditionChecker.isEligibleForDiscount("바비큐립", 8)); // 금요일(주말,할인)

       // 디저트가 평일에 할인되는지 테스트
        assertTrue(DiscountConditionChecker.isEligibleForDiscount("초코케이크", 3)); // 일요일 (할인)
        assertTrue(DiscountConditionChecker.isEligibleForDiscount("초코케이크", 7)); // 목요일 (할인)
        assertFalse(DiscountConditionChecker.isEligibleForDiscount("초코케이크", 9)); // 토요일 (주말)
    }

    // isEligibleForEvent 메서드 테스트: 이벤트 참가 자격 검증
    @Test
    public void 이벤트_참가_가능_테스트() {
        // 이벤트 참가에 필요한 최소 비용을 맞추고 음료만 주문하지 않았을 경우
        OrderList orderList = new OrderList();
        orderList.addItem("티본스테이크",1);

        assertFalse(DiscountConditionChecker.isEligibleForEvent(1000, orderList)); // 최소비용 불충족
        assertTrue(DiscountConditionChecker.isEligibleForEvent(100000, orderList)); // 최소비용 불충족

        OrderList orderList2 = new OrderList();
        orderList2.addItem("레드와인",1);
        // 최소 비용을 맞추지 못했거나 음료만 주문했을 경우
        OrderList beverageOnlyOrderList = new OrderList();
        assertFalse(DiscountConditionChecker.isEligibleForEvent(100000, beverageOnlyOrderList)); // 최소 비용 미충족
        assertFalse(DiscountConditionChecker.isEligibleForEvent(1000, beverageOnlyOrderList)); // 음료만 주문
    }
}