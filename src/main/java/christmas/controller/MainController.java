package christmas.controller;

import christmas.dto.OrderList;
import christmas.dto.PriceResult;
import christmas.service.OrderService;
import christmas.service.PriceService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {

    private final OrderService orderService;
    private final PriceService priceService;

    public MainController(OrderService orderService, PriceService priceService) {
        this.orderService = orderService;
        this.priceService = priceService;
    }

    public void start() {
        // 초기 화면 출력
        OutputView.printInitialMessage();
        // 방문일, 주문 메뉴 입력받기
        int visitDay = InputView.inputVisitDay();
        String orderInput = InputView.inputOrderMenu();
        // 주문리스트, 주문가격 생성
        OrderList orderList = orderService.createOrderListFromInput(orderInput);
        PriceResult priceResult = priceService.calculatePrice(orderList, visitDay);
        // 주문 상세 내역 출력
        OutputView.printOrderDetails(priceResult, orderList, visitDay);
    }
}