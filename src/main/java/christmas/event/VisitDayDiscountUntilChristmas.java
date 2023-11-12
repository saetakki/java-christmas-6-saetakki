package christmas.event;

import java.util.function.Function;

public class DailyDiscountUntilChristmas {

    Function<Integer, Function<Double, Double>> applyDailyDiscount = date -> total -> {
        if (date <= 25) {
            double discount = 1000 + (date - 1) * 100;
            return total - discount;
        }
        return total;
    };
}
