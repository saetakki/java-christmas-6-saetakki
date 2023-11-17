package christmas.utils;

import static christmas.validator.InputValidator.validateMenuAvailable;
import christmas.dto.OrderList;
import java.util.List;

public class OrderListManager {
    // 파싱되어 전달된 입력을 기반으로 주문 목록을 생성하는 메서드
    // 파싱된 데이터를 순회하여 판매중인 메뉴인지 유효성을 검사한 후
    // 유효한 메뉴만 주문 목록에 추가
    public static OrderList createOrderList(List<String[]> parsedInput) {
        OrderList orderList = new OrderList();
        parsedInput.forEach(data -> {
            // 메뉴 유효성 검사 후, 유효한 메뉴만 목록에 추가
            if (validateMenuAvailable(data[0])) {
                orderList.addItem(data[0], Integer.parseInt(data[1]));
            }
        });
        return orderList;
    }
}