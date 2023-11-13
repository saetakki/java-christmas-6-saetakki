package christmas.event;

import static christmas.data.Foods.FoodItem.getPriceOfMenu;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class CostCalculator {

    // cart에 담긴 모든 항목의 원가를 합산하는 함수
    private static int calculateTotalOriginalPrice(Map<String, Integer> cart) {
        return cart.entrySet().stream().
                map(calculateOriginalPricePerItem).reduce(0, (a, b) -> a + b);
    }

    // 장바구니의 각 항목 총액을 계산
    // getPriceOfMenu를 통해 해당 항목의 원가를 호출하고 수량(entry.getValue())를 곱해 항목 총액을 계산
    private static Function<Entry<String, Integer>, Integer> calculateOriginalPricePerItem =
            entry -> getPriceOfMenu(entry.getKey()) * entry.getValue();

}
