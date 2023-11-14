package christmas.event;

import static christmas.Constants.DISCOUNT_AMOUNT;
import static christmas.Constants.MINIMUM_SPEND_FOR_CHAMPAGNE;
import static christmas.data.Foods.FoodItem.getPriceOfMenu;
import static christmas.event.CostCalculator.calculateOriginalPricePerItem;
import static christmas.event.SpecialDayDiscountCalculator.applySpecialDayAndDdayDiscount;
import static christmas.event.DiscountConditionChecker.isEligibleForDiscount;

import java.util.Map;
import java.util.function.Function;

public class DiscountCalculator {
    // 날짜에 따른 할인을 적용한 후의 총 예상 결제금액을 계산하는 함수
    // 날짜별 할인 금액을 계산한 후, 1일부터 25일까지 진행되는 방문일 할인, 주말 및 크리스마스 당일의 추가할인 순으로 계산
    public static int calculateTotalDiscountedPrice(Map<String, Integer> cart, Integer date) {
        int basicDiscountAppliedPrice = cart.entrySet().stream().map(calculatePriceWithDiscountStrategy(date)).reduce(0, (a, b) -> a + b);
        Function<Integer, Integer> AdditionalDiscountApplier = applySpecialDayAndDdayDiscount(date);
        return AdditionalDiscountApplier.apply(basicDiscountAppliedPrice);
    }


    public static int calculateGiftPrice(Integer originalPrice, String gift){
        int GIFT_PRICE = getPriceOfMenu(gift);
        if(originalPrice >= MINIMUM_SPEND_FOR_CHAMPAGNE){
            return originalPrice-GIFT_PRICE;
        }
        return originalPrice;
    }

    // 날짜에 따라 장바구니 각 항목에 적용할 가격 계산 전략을 결정하는 함수
    // 할인이 적용되어야 하는 경우 할인 가격을, 그렇지 않은 경우 원가를 계산하여 반환
    private static Function<Map.Entry<String, Integer>, Integer> calculatePriceWithDiscountStrategy(Integer date){
        return entry -> {
            if(isEligibleForDiscount(entry.getKey(),date)){
                return calculateDiscountedPricePerItem.apply(entry);
            }
            return calculateOriginalPricePerItem.apply(entry);
        };
    };

    // 장바구니의 각 항목 할인가를 계산
    // getPriceOfMenu를 통해 해당 항목의 원가를 호출하고 수량(entry.getValue())를 곱해 항목 총액을 계산하되
    // 원가에 할인가를 적용하여 할인이 적용된 가격을 계산

    private static Function<Map.Entry<String, Integer>, Integer> calculateDiscountedPricePerItem = entry ->
            (getPriceOfMenu(entry.getKey()) - DISCOUNT_AMOUNT) * entry.getValue();

}
