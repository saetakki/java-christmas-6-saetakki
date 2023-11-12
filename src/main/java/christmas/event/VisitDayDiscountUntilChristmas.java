package christmas.event;

import java.util.function.Function;

public class VisitDayDiscountUntilChristmas {

    public static Function<Integer, Function<Integer, Integer>> applyDailyDiscount = date -> total -> {
        if (date <= 25) {
            Integer discount = 1000 + (date - 1) * 100;
            return total - discount;
        }
        return total;
    };
}
