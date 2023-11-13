package christmas.event;

public class VisitDayDiscountUntilChristmas {

    public static Integer applyDailyDiscount(Integer price, Integer date){
        if (date <= 25) {
            Integer discount = 1000 + (date - 1) * 100;
            return price - discount;
        }
        return price;
    };
}
