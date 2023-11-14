package christmas.event;

import static christmas.Constants.BADGES;
import static christmas.Constants.GRADE_PRICE;

import christmas.dto.PriceResult;

public class BadgeCalculator {

    protected static PriceResult priceResult;
    public static String badgeCalculator(Integer originalPrice, Integer IncludeGiftPrice) {
        int gap = (originalPrice - IncludeGiftPrice);
        for (int i = GRADE_PRICE.size() - 1; i >= 0; i--) {
            if (gap >= GRADE_PRICE.get(i)) {
                return BADGES.get(i);
            }
        }
        return "없음";
    }
}
