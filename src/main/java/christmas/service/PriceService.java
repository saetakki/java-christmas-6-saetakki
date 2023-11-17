package christmas.service;

import static christmas.Constants.MINIMUM_SPEND_FOR_GIFT;
import static christmas.event.CostCalculator.calculateTotalOriginalPrice;
import static christmas.event.DiscountCalculator.calculateTotalDiscountedPrice;
import static christmas.event.DiscountConditionChecker.isEligibleForEvent;

import christmas.dto.OrderList;
import christmas.dto.PriceResult;

public class PriceService {

    // 총 원가, 할인이 적용된 총액, 증정품 수령 여부를 계산
    public static PriceResult calculatePrice(OrderList orderList, int date) {
        int totalCost = calculateTotalOriginalPrice(orderList.getItems());
        int totalDiscountCost = calculateDiscountedPrice(totalCost, orderList, date);
        boolean isGiftEligible = checkGiftEligibility(totalCost, orderList);

        return new PriceResult(date, totalCost, totalDiscountCost, isGiftEligible);
    }

    // 할인이 적용된 총액을 계산
    // 주문 내역과 구매 총액을 기준으로 이벤트에 참여 가능한지 판단하는 isEligibleForEvent 함수로 계산 방식을 결정합니다
    // 만약 이벤트 대상이 아니라면 원가를, 이벤트 대상이라면 할인이 적용된 가격을 리턴합니다
    private static int calculateDiscountedPrice(int totalCost, OrderList orderList, int date) {
        if (isEligibleForEvent(totalCost, orderList)) {
            return calculateTotalDiscountedPrice(orderList.getItems(), totalCost, date);
        }
        return totalCost;
    }

    // 증정품 수령 여부 판단
    // 이벤트 참여 대상이면서 증정품을 수령할 수 있는 최소 소비금액인 MINIMUM_SPEND_FOR_GIFT 이상을 지출했는지 여부를 검사합니다
    // 모든 조건에 부합한다면 true, 아니라면 false를 리턴합니다
    private static boolean checkGiftEligibility(int totalCost, OrderList orderList) {
        return totalCost >= MINIMUM_SPEND_FOR_GIFT && isEligibleForEvent(totalCost, orderList);
    }
}
