package christmas;

import java.util.List;

public class Constants {

    // 에러 관련 에러메세지와 prefix
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

    // Input 관련 메세지가 담겨있는 enum
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

    // 출력과 관련있는 메세지들이 모여있는 enum
    public enum OutputMessage {
        GREETING_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),

        SHOW_NOTICE("\n<이벤트 유의사항>\n"
                +"총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.\n"
                        + "음료만 주문 시, 주문할 수 없습니다.\n"
                        + "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n"
                        + "(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)\n\n"
        ),

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

    // 평일/주말 이벤트 할인 때 적용될 이벤트 할인가
    public static int DISCOUNT_AMOUNT = 2023;
    // 증정품을 받기 위한 최소 소비 금액
    public static int MINIMUM_SPEND_FOR_GIFT = 120000;
    // 이벤트에 참여하기 위해 필요한 최소 소비 금액
    public static int MINIMUM_SPEND_FOR_EVENT = 10000;

    // 뱃지 등급별 최소 금액
    public static List<Integer> GRADE_PRICE = List.of(5000, 10000, 20000);

    // 뱃지 리스트
    public static List<String> BADGES = List.of("별", "트리", "산타");

    // 증정품으로 수여되는 품목
    public static String GIFT = "샴페인";
}
