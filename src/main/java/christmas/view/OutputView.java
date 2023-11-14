package christmas.view;

import static christmas.Constants.GIFT;
import static christmas.Constants.OutputMessage.SHOW_DISCOUNT_SUMMARY;
import static christmas.data.Foods.FoodItem.getPriceOfMenu;
import static christmas.event.BadgeCalculator.badgeCalculator;
import static christmas.event.DiscountConditionChecker.isEligibleForEvent;
import static christmas.event.SpecialDayDiscountCalculator.applyChristmasDdayDiscount;
import static christmas.event.SpecialDayDiscountCalculator.applySpecialDayDiscount;
import static christmas.event.dateChecker.todayIs;

import christmas.Constants.OutputMessage;
import christmas.dto.OrderList;
import christmas.dto.PriceResult;
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

    //주문 메뉴
    public static void printOrder(OrderList orderList){
        String orderText = orderList.getItems().entrySet().stream()
                        .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                        .collect(Collectors.joining("\n\n"));

        System.out.println(orderText+"\n");
    }

    //할인 전 총주문 금액
    public static void printOriginalPrice(String message, int price) {
        System.out.println(message+"\n"+formatPrice(price)+"\n");
    }
    //증정 메뉴
    public static void printGiftEligibility(PriceResult priceResult){
        String giftMessage = OutputMessage.SHOW_NOTHING.getMessage();
        System.out.println(OutputMessage.SHOW_GIFT_TITLE.getMessage());
        if(priceResult.getGift()){
            giftMessage = GIFT+ " 1개";
        }
        System.out.println(giftMessage+"\n");
    }



    @FunctionalInterface
    interface DiscountDetailProvider {
        String getDiscountDetail();
    }

    //혜택 내역
    public static void printDiscountList(int originalPrice, int discountedPrice, int date, OrderList orderList){
        String discountDetails = getDiscountDetails(originalPrice, discountedPrice, date, orderList);
        System.out.println(SHOW_DISCOUNT_SUMMARY.getMessage());
        String discountList = discountDetails;
        if(discountDetails.isEmpty()){
            discountList="없음";
        }
        System.out.println(discountList+"\n");
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




    private static String getDiscountDetails(int originalPrice, int discountedPrice, int date, OrderList orderList){
        String details = getDdayDiscountDetail(date)
                + getMainOrDessertDiscountDetail(originalPrice, discountedPrice, date)
                + getSpecialDayDiscountDetail(date)
                + getChampagneEventDetail(originalPrice, orderList);

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

    private static String getChampagneEventDetail(int originalPrice, OrderList orderList){
        return getDiscountDetail(() -> {
            int giftPrice = getPriceOfMenu(GIFT);
            if (isEligibleForEvent(originalPrice, orderList)) {
                return "증정 이벤트: " + formatPrice(giftPrice*-1);
            }
            return "";
        });
    }



    public static void printDiscountAmount(String message, Integer originalPrice, Integer discountedPrice, boolean getGift) {
        System.out.println(message);
        Integer totalDiscountAmount = discountedPrice-originalPrice;
        if(getGift){
           totalDiscountAmount -= getPriceOfMenu(GIFT);
        }
        System.out.println(formatPrice(totalDiscountAmount));
    }

    public static void printBadge(int originalPrice,int discountedPrice, boolean getGift) {
        System.out.println(OutputMessage.SHOW_BADGE.getMessage());
        String badge = badgeCalculator(originalPrice,discountedPrice, getGift);
        System.out.println(badge);
    }

    public static String formatPrice(int price) {
        return String.format("%,d원", price);
    }


}
