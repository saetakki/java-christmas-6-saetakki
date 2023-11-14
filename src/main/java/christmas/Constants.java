package christmas;

import java.util.List;

public class Constants {
    private static final String ERROR_PREFIX = "[ERROR] ";
    public enum ErrorMessage {
        INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
        INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요.");

        private final String message;

        ErrorMessage(String err) {
            this.message = err;
        }

        public String getMessage() {
            return Constants.ERROR_PREFIX + this.message;
        }
    }

    public enum InputMessage {
        INPUT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
        INPUT_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        private final String message;

        InputMessage(String msg) {
            this.message = msg;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public enum OutputMessage {
        GREETING_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),


        SHOW_MENU("<애피타이저>\n"
                + "양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)\n"
                + "\n"
                + "<메인>\n"
                + "티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)\n"
                + "\n"
                + "<디저트>\n"
                + "초코케이크(15,000), 아이스크림(5,000)\n"
                + "\n"
                + "<음료>\n"
                + "제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)"),
        SHOW_ORIGINAL_PRICE_TITLE("<할인 전 총주문 금액>"),
        SHOW_GIFT_TITLE("<증정 메뉴>"),
        SHOW_DISCOUNT_SUMMARY("<혜택 내역>"),
        SHOW_TOTAL_DISCOUNT_AMOUNT("<총혜택 금액>"),
        SHOW_DISCOUNTED_PRICE_TITLE("<할인 후 예상 결제 금액>"),
        SHOW_BADGE("<12월 이벤트 배지>"),

        SHOW_ZERO("0원"),
        SHOW_NOTHING("없음");


        private final String message;

        OutputMessage(String msg) {
            this.message = msg;
        }

        public String getMessage() {
            return this.message;
        }
    }
    public static int DISCOUNT_AMOUNT = 2023;
    public static int MINIMUM_SPEND_FOR_CHAMPAGNE = 120000;

    public static List<Integer> GRADE_PRICE = List.of(5000, 10000, 20000);
    public static List<String> BADGES = List.of("별", "트리", "산타");

    public static String GIFT = "샴페인";
}
