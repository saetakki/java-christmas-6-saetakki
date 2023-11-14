package christmas.controller;

import static christmas.view.OutputView.printDiscountDetails;
import static christmas.view.OutputView.printFinalPriceAndBadge;
import static christmas.view.OutputView.printGreetingMessage;
import static christmas.view.OutputView.printMenu;
import static christmas.view.OutputView.printOrder;
import static christmas.view.OutputView.printPriceDetails;

import christmas.dto.OrderList;
import christmas.dto.PriceResult;
import christmas.view.InputView;

public class MainController {

    private static void printOrderDetails(PriceResult priceResult, OrderList orderList, Integer date) {
        System.out.println("<주문 메뉴>");
        printOrder(orderList);
        printPriceDetails(priceResult);
        printDiscountDetails(priceResult, orderList, date);
        printFinalPriceAndBadge(priceResult);
    }
    private static void printInitalPrint(){
        printGreetingMessage();
        printMenu();
    }

    public static void start() {
        printInitalPrint();
        int date = InputView.inputVisitDay();
        String order = InputView.inputMenu();
        System.out.println('\n');
        OrderList orderList = OrderController.createOrderListFromInput(order);
        PriceResult priceResult = PriceController.calculatePrice(orderList, date);
        printOrderDetails(priceResult, orderList, date);
    }
}