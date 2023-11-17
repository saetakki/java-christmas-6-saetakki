package christmas.event;

import static christmas.service.PriceService.calculatePrice;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.dto.OrderList;
import christmas.dto.PriceResult;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {

    @Test
    public void 할인_이벤트_적용_테스트() {
        // 테스트 케이스 설정
        Map<String, Integer> cart = new HashMap<>();
        cart.put("양송이수프", 1); //  6000
        cart.put("바비큐립", 1);  // 54000
        cart.put("제로콜라", 2);  // 6000

        int originalTotalCost = 66000; // 가정된 원래 총 가격
        int date = 25;               // 크리스마스 당일

        // 할인된 총 가격 계산
        int discountedTotal = DiscountCalculator.calculateTotalDiscountedPrice(cart, originalTotalCost, date);
        // 예상 결과 계산
        int expectedDiscountedTotal = 61600;
        assertEquals(expectedDiscountedTotal, discountedTotal);
    }

    @Test
    public void 할인_이벤트_미적용_테스트() {
        OrderList orderList = new OrderList();
        orderList.addItem("레드와인",2);

        int originalTotalCost = 120000; // 가정된 원래 총 가격
        int date = 31;               // 크리스마스 당일

        // 할인된 총 가격 계산
        PriceResult discountedTotal = calculatePrice(orderList, date);
        int expectedDiscountedTotal = 120000;
        assertEquals(expectedDiscountedTotal, discountedTotal.getDiscountedPrice());
    }

}