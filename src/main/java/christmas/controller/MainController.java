package christmas.controller;

import static christmas.Constants.OutputMessage.SHOW_DISCOUNTED_PRICE_TITLE;
import static christmas.Constants.OutputMessage.SHOW_ORIGINAL_PRICE_TITLE;
import static christmas.Constants.OutputMessage.SHOW_TOTAL_DISCOUNT_AMOUNT;
import static christmas.view.OutputView.print;
import static christmas.view.OutputView.printBadge;
import static christmas.view.OutputView.printDiscountAmount;
import static christmas.view.OutputView.printDiscountList;
import static christmas.view.OutputView.printGiftEligibility;
import static christmas.view.OutputView.printGreetingMessage;
import static christmas.view.OutputView.printMenu;
import static christmas.view.OutputView.printOrder;
import static christmas.view.OutputView.printOriginalPrice;

import christmas.dto.OrderList;
import christmas.dto.PriceResult;
import christmas.view.InputView;

public class MainController {
    private static void printOrderDetails(PriceResult priceResult, OrderList orderList, Integer date){
        print.accept("<주문 메뉴>\n");
        // 주문 메뉴
        printOrder(orderList);
        //할인 전 총주문 금액
        printOriginalPrice(SHOW_ORIGINAL_PRICE_TITLE.getMessage(), priceResult.getOriginalPrice());
        //증정 메뉴
        printGiftEligibility(priceResult);
        //혜택 내역
        printDiscountList(priceResult.getOriginalPrice(), priceResult.getDiscountedPrice(), date, orderList);
        //총혜택 금액
        printDiscountAmount(SHOW_TOTAL_DISCOUNT_AMOUNT.getMessage(), priceResult.getOriginalPrice(),priceResult.getDiscountedPrice(), priceResult.getGift());
        //할인 후 예상 결제 금액
        printOriginalPrice(SHOW_DISCOUNTED_PRICE_TITLE.getMessage(), priceResult.getDiscountedPrice());
        //뱃지
        printBadge(priceResult.getOriginalPrice(), priceResult.getDiscountedPrice(), priceResult.getGift());
    }

    public static void start() {
        printGreetingMessage();
        printMenu();
        int date = InputView.inputVisitDay();
        String order = InputView.inputMenu();
        System.out.println('\n');

//        int date = simpleInput();
//        String order = simpleMenuInput();
//


        OrderList orderList = OrderController.createOrderListFromInput(order);
        PriceResult priceResult = PriceController.calculatePrice(orderList, date);

        printOrderDetails(priceResult, orderList, date);

    }
}