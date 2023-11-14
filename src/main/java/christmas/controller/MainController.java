package christmas.controller;

import static christmas.Constants.GIFT;
import static christmas.Constants.OutputMessage.SHOW_DISCOUNTED_PRICE_TITLE;
import static christmas.Constants.OutputMessage.SHOW_ORIGINAL_PRICE_TITLE;
import static christmas.Constants.OutputMessage.SHOW_TOTAL_DISCOUNT_AMOUNT;
import static christmas.view.OutputView.printBadge;
import static christmas.view.OutputView.printDiscountAmount;
import static christmas.view.OutputView.printDiscountList;
import static christmas.view.OutputView.printGiftList;
import static christmas.view.OutputView.printOrder;
import static christmas.view.OutputView.printPrice;

import christmas.dto.OrderList;
import christmas.dto.PriceResult;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {
        public static int date;
    public static void start() {

        OutputView.printGreetingMessage();
        OutputView.printMenu();

        date = InputView.inputVisitDay();
        String order = InputView.inputMenu();

        OrderList orderList = OrderController.createOrderListFromInput(order);
        PriceResult priceResult = PriceController.calculatePrice(orderList.getItems(), date);

        printOrder(orderList);

        printGiftList(priceResult.getOriginalPrice(), GIFT);


        printPrice(SHOW_ORIGINAL_PRICE_TITLE.getMessage(), priceResult.getOriginalPrice());
        printDiscountAmount(SHOW_TOTAL_DISCOUNT_AMOUNT.getMessage(), priceResult.getOriginalPrice(),priceResult.getIncludeGiftPrice());
        printPrice(SHOW_DISCOUNTED_PRICE_TITLE.getMessage(),priceResult.getDiscountedPrice());
        printDiscountList(priceResult.getOriginalPrice(), priceResult.getDiscountedPrice(), date);
        printBadge(priceResult.getOriginalPrice(),priceResult.getIncludeGiftPrice());

    }
}