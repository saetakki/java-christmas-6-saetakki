//package christmas.service;
//
//import static christmas.utils.InputParser.parseInputToArray;
//
//import christmas.dto.OrderList;
//import christmas.utils.OrderListManager;
//import java.util.List;
//
//public class OrderController {
//    public static OrderList createOrderListFromInput(String orderInput){
//        List<String[]> parsedInput = parseInputToArray(orderInput);
//        return OrderListManager.createOrderList(parsedInput);
//    }
//
//}
//
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