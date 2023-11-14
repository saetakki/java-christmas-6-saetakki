package christmas.controller;

import static christmas.Constants.MINIMUM_SPEND_FOR_GIFT;
import static christmas.event.CostCalculator.calculateTotalOriginalPrice;
import static christmas.event.DiscountCalculator.calculateTotalDiscountedPrice;
import static christmas.event.DiscountConditionChecker.isEligibleForEvent;

import christmas.dto.OrderList;
import christmas.dto.PriceResult;
import java.util.Map;

public class PriceController {

    public static PriceResult calculatePrice(OrderList orderList, Integer date) {
        Map<String, Integer> cart = orderList.getItems();
        int totalCost = calculateTotalOriginalPrice(cart);
        int totalDiscountCost = totalCost;
        boolean isGiftEligible = false;

        // 주문 금액이 최소 지출 기준 미만이거나 주문 항목이 음료만 포함된 경우 할인을 적용하지 않음
        if (isEligibleForEvent(totalCost, orderList)) {
            totalDiscountCost = calculateTotalDiscountedPrice(cart, totalCost, date);
        }
        if (totalCost >= MINIMUM_SPEND_FOR_GIFT && isEligibleForEvent(totalCost,orderList)){
            isGiftEligible = true;
        }

        return new PriceResult(date, totalCost, totalDiscountCost, isGiftEligible);
    }
}
