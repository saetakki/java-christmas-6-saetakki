package christmas.service;

import christmas.dto.OrderList;
import christmas.utils.OrderListManager;
import christmas.utils.InputParser;
import java.util.List;

public class OrderService {
    public OrderList createOrderListFromInput(String orderInput){
        List<String[]> parsedInput = InputParser.parseInputToArray(orderInput);
        return OrderListManager.createOrderList(parsedInput);
    }
}