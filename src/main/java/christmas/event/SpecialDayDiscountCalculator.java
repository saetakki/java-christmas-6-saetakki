package christmas.event;

import static christmas.event.dateChecker.isSundayOrChristmas;

import java.util.function.Function;


// 요일, 기간과 관련된 할인 로직을 담은 클래스
// 크리스마스 디데이 할인 로직, 특별일 할인 로직에 해당
public class SpecialDayDiscountCalculator {
    public final static int SPECIAL_DAY_DISCOUNT_AMOUNT = 1000;
    // 25일까지 매일 100원씩 할인 금액이 증가하는 디데이 할인을 계산하기 위한 메서드
    // 크리스마스 당일까지 진행되기 때문에 입력일이 25일 이전인지 여부를 판별
    // 이벤트 기간이라면 공식을 통해서 할인 금액 계산 후 적용
    public static Integer applyChristmasDdayDiscount(Integer price, Integer date){
        if (date <= 25) {
            Integer discount = 1000 + (date - 1) * 100;
            return price - discount;
        }
        return price;
    }

    // 특별일 할인 금액을 적용하는 메서드
    // 매주 일요일 및 크리스마스 당일에 진행하는 이벤트이기 떄문에 입력일이 일요일 혹은 크리스마스 당일인지 검증하고
    // 이벤트 해당일이라면 해당 할인 금액 적용

    public static Integer applySpecialDayDiscount(Integer price, Integer date){
        if (isSundayOrChristmas(date)) {
            return price - SPECIAL_DAY_DISCOUNT_AMOUNT;
        }
        return price;
    }

    // 위의 두 이벤트 금액을 적용하는 메서드
    // date를 인수로 받아서 Functino 객체를 반환
    // date를 입력받으면 크리스마스 디데이 이벤트, 특별일 이벤트 할인을 적용한 discountedPrice를 리턴

    public static Function<Integer, Integer> applySpecialDayAndDdayDiscount(Integer date){
        return totalCost -> {
            int discountedPrice = applyChristmasDdayDiscount(totalCost, date);
           discountedPrice = applySpecialDayDiscount(discountedPrice,date);
           return discountedPrice;
        };
    }
}
