package christmas.utils;

import static christmas.validator.InputValidator.validateMenuAvailable;
import christmas.dto.OrderList;
import java.util.List;

public class OrderListManager {
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