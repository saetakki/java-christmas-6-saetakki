package christmas.controller;
import static christmas.Constants.GIFT;
import static christmas.event.CostCalculator.calculateTotalOriginalPrice;
import static christmas.event.DiscountCalculator.calculateGiftPrice;
import static christmas.event.DiscountCalculator.calculateTotalDiscountedPrice;

import christmas.dto.PriceResult;
import java.util.Map;
public class PriceController {



    // 계산된 총 원가, 할인이 적용된 예상 결제금액을 dto에 담는 함수
    // 할인 전 가격과 할인 후 가격을 PriceResult 객체로 포장하여 반환
    public static PriceResult calculatePrice(Map<String, Integer> cart, Integer date) {
        int totalCost = calculateTotalOriginalPrice(cart);
        int totalDiscountCost = calculateTotalDiscountedPrice(cart, date);
        int includeGiftPrice = calculateGiftPrice(totalDiscountCost,GIFT);
        return new PriceResult(totalCost, totalDiscountCost,includeGiftPrice);
    }

};

