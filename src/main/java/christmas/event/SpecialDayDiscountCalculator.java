package christmas.event;

import static christmas.event.dateChecker.isSundayOrChristmas;
import java.util.function.Function;

public class SpecialDayDiscountCalculator {
    public final static int SPECIAL_DAY_DISCOUNT_AMOUNT = 1000;

    public static Integer applyChristmasDdayDiscount(Integer price, Integer date){
        if (date <= 25) {
            Integer discount = 1000 + (date - 1) * 100;
            return price - discount;
        }
        return price;
    }

    public static Integer applySpecialDayDiscount(Integer price, Integer date){
        if (isSundayOrChristmas(date)) {
            return price - SPECIAL_DAY_DISCOUNT_AMOUNT;
        }
        return price;
    }

    public static Function<Integer, Integer> applySpecialDayAndDdayDiscount(Integer date){
        return price -> {
            int discountedPrice = applyChristmasDdayDiscount(price, date);
           discountedPrice = applySpecialDayDiscount(discountedPrice,date);
           return discountedPrice;
        };
    }
}
