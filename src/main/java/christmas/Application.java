package christmas;

import christmas.controller.MainController;
import christmas.service.OrderService;
import christmas.service.PriceService;

public class Application {
    public static void main(String[] args) {
        // 서비스 인스턴스 생성
        OrderService orderService = new OrderService();
        PriceService priceService = new PriceService();

        // MainController 인스턴스 생성 및 의존성 주입
        MainController mainController = new MainController(orderService, priceService);

        // MainController 시작
        mainController.start();
    }
}