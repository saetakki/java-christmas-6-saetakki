package christmas.event;

import static christmas.event.dateChecker.isSundayOrChristmas;

import java.util.function.Function;

public class VisitDayDiscountUntilChristmas {
    public final static int SPECIAL_DAY_DISCOUNT_AMOUNT = 1000;

    public static Integer applyDailyDiscount(Integer price, Integer date){
        if (date <= 25) {
            Integer discount = 1000 + (date - 1) * 100;
            return price - discount;
        }
        return price;
    };

    public static Integer applyDesignatedDayDiscount(Integer price, Integer date){
        if (isSundayOrChristmas(date)) {
            return price - SPECIAL_DAY_DISCOUNT_AMOUNT;
        }
        return price;
    };

    public static Function<Integer, Integer> applyAdditionalDiscount(Integer date){
        return price -> {
            int discountedPrice = applyDailyDiscount(price, date);
           discountedPrice = applyDesignatedDayDiscount(discountedPrice,date);
           return discountedPrice;
        };
    };
}
