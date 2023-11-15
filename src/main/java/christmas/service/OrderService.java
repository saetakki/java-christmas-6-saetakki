package christmas.service;

import christmas.dto.OrderList;
import christmas.utils.OrderListManager;
import christmas.utils.InputParser;
import java.util.List;

public class OrderService {
    // 주문 입력 문자열로부터 주문 목록을 생성하는 메서드
    public OrderList createOrderListFromInput(String orderInput){
        // 입력 문자열을 파싱하여 리스트로 변환한 후
        List<String[]> parsedInput = InputParser.parseInputToArray(orderInput);
        // ORderListManager를 사용하여 주문 목록 생성 후 반환
        return OrderListManager.createOrderList(parsedInput);
    }
}