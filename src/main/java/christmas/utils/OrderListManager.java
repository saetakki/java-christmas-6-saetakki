package christmas.utils;

import christmas.dto.OrderList;
import java.util.List;

public class OrderListManager {
    public static OrderList createOrderList(List<String[]> parsedInput) {
        OrderList orderList = new OrderList();
        parsedInput.forEach(data -> orderList.addItem(data[0], Integer.parseInt(data[1])));
        return orderList;
    }
}
