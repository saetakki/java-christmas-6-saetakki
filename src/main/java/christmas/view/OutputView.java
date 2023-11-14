package christmas.view;

import static christmas.Constants.GIFT;
import static christmas.Constants.MINIMUM_SPEND_FOR_CHAMPAGNE;
import static christmas.Constants.OutputMessage.SHOW_DISCOUNT_SUMMARY;
import static christmas.Constants.OutputMessage.SHOW_GIFT_TITLE;
import static christmas.data.Foods.FoodItem.getPriceOfMenu;
import static christmas.event.BadgeCalculator.badgeCalculator;
import static christmas.event.SpecialDayDiscountCalculator.applyChristmasDdayDiscount;
import static christmas.event.SpecialDayDiscountCalculator.applySpecialDayDiscount;
import static christmas.event.dateChecker.todayIs;

import christmas.Constants.OutputMessage;
import christmas.dto.OrderList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class OutputView {

    public static void printGreetingMessage(){
        System.out.println(OutputMessage.GREETING_MESSAGE.getMessage());
    }

    public static void printMenu(){
        System.out.println(OutputMessage.SHOW_MENU.getMessage());
    }

    public static Consumer<String> print = System.out::println;


    public static void printOrder(OrderList orderList){
        String orderText = "<주문 메뉴>\n" +
                orderList.getItems().entrySet().stream()
                        .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                        .collect(Collectors.joining("\n"));
        print.accept(orderText);
    }


    public static void printPrice(String message, int price) {
        print.accept(message+"\n"+formatPrice(price));

    }

    public static void printGiftList(int originalPrice, String gift) {
        String msg = Optional.of(originalPrice)
                .filter(price -> price >= MINIMUM_SPEND_FOR_CHAMPAGNE)
                .map(price -> gift + " 1개")
                .orElse(OutputMessage.SHOW_NOTHING.getMessage());
        print.accept(SHOW_GIFT_TITLE.getMessage() + "\n" + msg);
    }


    @FunctionalInterface
    interface DiscountDetailProvider {
        String getDiscountDetail();
    }

    public static void printDiscountList(int originalPrice, int discountedPrice, int date){
        String discountDetails = getDiscountDetails(originalPrice, discountedPrice, date);
        System.out.println(SHOW_DISCOUNT_SUMMARY.getMessage());
        String discountList = discountDetails;
        if(discountDetails.isEmpty()){
            discountList="없음";
        }
        System.out.println(discountList);
    }

    private static String getDiscountDetail(DiscountDetailProvider provider) {
        String detail = provider.getDiscountDetail();
        if (!detail.isEmpty()) {
            detail += "\n";
        }
        return detail;
    }


    private static String getDdayDiscountDetail(int date){
        return getDiscountDetail(() -> {
            int discount = applyChristmasDdayDiscount(0, date);
            if (discount < 0) {
                return "크리스마스 디데이 할인: " + formatPrice(discount);
            }
            return "";
        });
    }




    private static String getDiscountDetails(int originalPrice, int discountedPrice, int date){
        String details = getDdayDiscountDetail(date)
                + getMainOrDessertDiscountDetail(originalPrice, discountedPrice, date)
                + getSpecialDayDiscountDetail(date)
                + getChampagneEventDetail(originalPrice);

        return details.trim();
    }


    private static String getMainOrDessertDiscountDetail(int originalPrice, int discountedPrice, int date){
        return getDiscountDetail(() -> {
            int discount = discountedPrice - originalPrice - applySpecialDayDiscount(0, date) - applyChristmasDdayDiscount(0, date);
            if (discount < 0) {
                return todayIs(date) + " 할인: " + formatPrice(discount);
            }
            return "";
        });
    }



    private static String getSpecialDayDiscountDetail(int date){
        return getDiscountDetail(() -> {
            int discount = applySpecialDayDiscount(0, date);
            if (discount < 0) {
                return "특별 할인: " + formatPrice(discount);
            }
            return "";
        });
    }

    private static String getChampagneEventDetail(int originalPrice){
        return getDiscountDetail(() -> {
            int giftPrice = getPriceOfMenu(GIFT);
            if (originalPrice >= MINIMUM_SPEND_FOR_CHAMPAGNE) {
                return "증정 이벤트: " + formatPrice(giftPrice*-1);
            }
            return "";
        });
    }

















    public static void printDiscountAmount(String message, int originaPrice,int includeGiftPrice) {
        System.out.println(message);
        System.out.println(formatPrice(includeGiftPrice-originaPrice));
    }

    public static void printBadge(int originalPrice,int includeGiftPrice) {
        System.out.println(OutputMessage.SHOW_BADGE.getMessage());
        String badge = badgeCalculator(originalPrice,includeGiftPrice);
        System.out.println(badge);
    }

    public static String formatPrice(int price) {
        return String.format("%,d원", price);
    }


}
