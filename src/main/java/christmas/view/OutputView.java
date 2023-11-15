package christmas.view;

import static christmas.Constants.GIFT;
import static christmas.Constants.OutputMessage.SHOW_DISCOUNT_SUMMARY;
import static christmas.Constants.OutputMessage.SHOW_ORIGINAL_PRICE_TITLE;
import static christmas.Constants.OutputMessage.SHOW_TOTAL_DISCOUNT_AMOUNT;
import static christmas.Constants.OutputMessage.SHOW_DISCOUNTED_PRICE_TITLE;
import static christmas.data.Foods.FoodItem.getPriceOfMenu;
import static christmas.event.BadgeCalculator.badgeCalculator;
import static christmas.event.DiscountConditionChecker.isEligibleForEvent;
import static christmas.event.SpecialDayDiscountCalculator.applyChristmasDdayDiscount;
import static christmas.event.SpecialDayDiscountCalculator.applySpecialDayDiscount;
import static christmas.event.dateChecker.todayIs;

import christmas.Constants.OutputMessage;
import christmas.dto.OrderList;
import christmas.dto.PriceResult;
import java.util.stream.Collectors;

public class OutputView {

    public static void printOrderDetails(PriceResult priceResult, OrderList orderList, Integer date) {
        System.out.println("<주문 메뉴>");
        printOrder(orderList);
        printPriceDetails(priceResult);
        printDiscountDetails(priceResult, orderList, date);
        printFinalPriceAndBadge(priceResult);
    }

    public static void printInitialMessage(){
        printGreetingMessage();
        printMenu();
    }


    // 인사말 출력
    public static void printGreetingMessage(){
        System.out.println(OutputMessage.GREETING_MESSAGE.getMessage());
    }

    // 메뉴판을 출력하는 함수
    public static void printMenu(){
        System.out.println(OutputMessage.SHOW_MENU.getMessage());
    }

    public static void printPriceDetails(PriceResult priceResult) {
        // 할인 전 총주문 금액
        printOriginalPrice(SHOW_ORIGINAL_PRICE_TITLE.getMessage(),priceResult.getOriginalPrice());
        // 증정 메뉴
        // 어떤 증정품을 받았는지 출력하는 함수
        printGiftEligibility(priceResult);
    }
    public static void printDiscountDetails(PriceResult priceResult, OrderList orderList, int date) {
        printDiscountList(priceResult.getOriginalPrice(), priceResult.getDiscountedPrice(), date, orderList, priceResult.getGift());
    }

    public static void printFinalPriceAndBadge(PriceResult priceResult) {
        printDiscountAmount(SHOW_TOTAL_DISCOUNT_AMOUNT.getMessage(), priceResult.getOriginalPrice(), priceResult.getDiscountedPrice(), priceResult.getGift());
        printOriginalPrice(SHOW_DISCOUNTED_PRICE_TITLE.getMessage(), priceResult.getDiscountedPrice());
        printBadge(priceResult.getOriginalPrice(), priceResult.getDiscountedPrice(), priceResult.getGift());
    }


    // 주문 메뉴
    // 메뉴-수량 형태로 되어있는 orderList를 입력받아 map으로 순회하면서 "메뉴 n개" 형태의 문자열로 재조합
    public static void printOrder(OrderList orderList){
        String orderText = orderList.getItems().entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
        System.out.println(orderText+"\n");
    }

    // 할인 전 총주문 금액
    public static void printOriginalPrice(String message, int price) {
        System.out.println(message+"\n"+formatPrice(price)+"\n");

    }
    // 증정 메뉴
    // 어떤 증정품을 받았는지 출력하는 함수
    public static void printGiftEligibility(PriceResult priceResult){
        String giftMessage = OutputMessage.SHOW_NOTHING.getMessage();
        System.out.println(OutputMessage.SHOW_GIFT_TITLE.getMessage());
        if(priceResult.getGift()){
            giftMessage = GIFT+ " 1개";
        }
        System.out.println(giftMessage+"\n");
    }


    // getDiscountDetail이라는 추상 메서드를 제공하는 함수형 인터페이스
    @FunctionalInterface
    interface DiscountDetailProvider {
        String getDiscountDetail();
    }

    // DiscountDetailProvider 인터페이스 구현 객체를 받아 해당 객체의 getDiscountDetail 메서드를 호출
    // 이를 통해 할인 정보를 문자열로 가져오는 역할
    // 할인 정보 문자열(detail)이 비어있지 않다면 개행문자를 추가하여 줄 바꾸고 반환
    private static String getDiscountDetail(DiscountDetailProvider provider) {
        String detail = provider.getDiscountDetail();
        if (!detail.isEmpty()) {
            detail += "\n";
        }
        return detail;
    }

    //원가, 할인가, 예약일, 주문 내역, 증정품 수령 여를을 입력받아 각각의 데이터 처리 함수에 인자로 전달하고 details에 합침
    //이후 공백을 제거하고 리턴함
    private static String getDiscountDetails(int originalPrice, int discountedPrice, int date, OrderList orderList, boolean isGiftEligibable){
        String details = getDdayDiscountDetail(date)
                + getMainOrDessertDiscountDetail(originalPrice, discountedPrice, date)
                + getSpecialDayDiscountDetail(date)
                + getChampagneEventDetail(originalPrice, orderList, isGiftEligibable);

        return details.trim();
    }


    // 원가, 할인가, 예약일, 주문 내역, 증정품 수령 여부를 입력받아 getDiscountDetails을 통해 하나의 문자열(discountDetails)을 만듦
    // 만약 문자열이 하나도 없다면 아무 할인도 못받았다는 것이기에 "없음"을 할당하고 출력
    // 그렇지 않다면 할인내역 출력
    public static void printDiscountList(int originalPrice, int discountedPrice, int date, OrderList orderList,
                                         Boolean isGiftEligible){
        String discountDetails = getDiscountDetails(originalPrice, discountedPrice, date, orderList,isGiftEligible);
        System.out.println(SHOW_DISCOUNT_SUMMARY.getMessage());
        String discountList = discountDetails;
        if(discountDetails.isEmpty()){
            discountList="없음";
        }
        System.out.println(discountList+"\n");
    }




    //디데이 이벤트(25일까지 점진적으로 할인금액 커지는 이벤트)로 받은 총 할인 금액을 나타내는 함수
    // 예약일을 인자로 받아 해당일까지의 할인 금액을 계산하고
    // 할인을 받았다면 "크리스마스 디데이 할인: 할인금액" 을 리턴하고 그렇지 않다면 빈 문자열 리턴
    // 음수인 이유는 단순히 예제에서 할인 금액을 음수로 표현했기 때문
    private static String getDdayDiscountDetail(int date){
        return getDiscountDetail(() -> {
            int discount = applyChristmasDdayDiscount(0, date);
            if (discount < 0) {
                return "크리스마스 디데이 할인: " + formatPrice(discount);
            }
            return "";
        });
    }


    // 원가, 할인가, 예약일을 인자로 받아 디저트 혹은 메인 코스음식 할인 가격을 나타내는 함수
    // 할인금액 종류는 계산할 때에는 별표가 그려진 특정일, 25일 이전까지의 모든 요일, 평일/주말에 따른 음식 할인이 존재
    // 그렇기 때문에 원가에서 디데이 할인가, 특별일 할인가를 빼고 금액이 남으면 추가적으로 평일/주말의 음식 할인을 받은 것
    // 이를 표시하는 함수
    private static String getMainOrDessertDiscountDetail(int originalPrice, int discountedPrice, int date){
        return getDiscountDetail(() -> {
            int discount = discountedPrice - originalPrice - applySpecialDayDiscount(0, date) - applyChristmasDdayDiscount(0, date);
            if (discount < 0) {
                return todayIs(date) + " 할인: " + formatPrice(discount);
            }
            return "";
        });
    }



    //달력에 표시된 요일에 방문하면 할인해 주는 금액을 표시하는 함수
    private static String getSpecialDayDiscountDetail(int date){
        return getDiscountDetail(() -> {
            int discount = applySpecialDayDiscount(0, date);
            if (discount < 0) {
                return "특별 할인: " + formatPrice(discount);
            }
            return "";
        });
    }

    // 증정품을 받았는지 여부를 표시하는 함수
    // isEligibleForEvent 함수와 isGiftEligible 값을 통해 이벤트 참가 자격이 있는 대상인지 검증 후
    // 증정품을 받은 사람이라면 증정 이벤트 문구 출력
    private static String getChampagneEventDetail(int originalPrice, OrderList orderList, boolean isGiftEligible){
        return getDiscountDetail(() -> {
            int giftPrice = getPriceOfMenu(GIFT);
            if (isEligibleForEvent(originalPrice, orderList) && isGiftEligible) {
                return "증정 이벤트: " + formatPrice(giftPrice*-1);
            }
            return "";
        });
    }

    // 혜택금액을 출력하는 함수
    // 혜택 금액은 기존 할인가에 증정품을 받았다면 증점품 가격까지 포함되기 떄문에 증정품을 받았는지 여부를 입력받음
    // 증정품을 받았다면 혜택 금액 갱신
    public static void printDiscountAmount(String message, Integer originalPrice, Integer discountedPrice, boolean isGiftEligible) {
        System.out.println(message);
        Integer totalDiscountAmount = discountedPrice-originalPrice;
        if(isGiftEligible){
            totalDiscountAmount -= getPriceOfMenu(GIFT);
        }
        System.out.println(formatPrice(totalDiscountAmount)+"\n");
    }

    // 뱃지 등급 출력하는 함수
    // 뱃지 등급은 혜택 금액을 기준으로 하기 때문에 증정품을 받았는지 여부를 입력받음
    // 뱃지 등급 계산 후 출력
    public static void printBadge(int originalPrice,int discountedPrice, boolean isGiftEligible) {
        System.out.println(OutputMessage.SHOW_BADGE.getMessage());
        String badge = badgeCalculator(originalPrice,discountedPrice, isGiftEligible);
        System.out.println(badge);
    }

    // 3자리마다 쉼표를 찍는 포맷
    public static String formatPrice(int price) {
        return String.format("%,d원", price);
    }


}
