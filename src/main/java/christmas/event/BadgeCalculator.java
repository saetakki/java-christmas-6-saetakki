package christmas.event;

import static christmas.Constants.BADGES;
import static christmas.Constants.GIFT;
import static christmas.Constants.GRADE_PRICE;
import static christmas.data.Foods.FoodItem.getPriceOfMenu;

public class BadgeCalculator {

    public static String badgeCalculator(Integer originalPrice, Integer discountedPrice, boolean isGiftEligible) {
        int gap = originalPrice - discountedPrice;
        if (isGiftEligible) {
            gap += getPriceOfMenu(GIFT); // 증정품을 받았다면 가격에 추가
        }
        for (int i = GRADE_PRICE.size() - 1; i >= 0; i--) {
            if (gap >= GRADE_PRICE.get(i)) {
                return BADGES.get(i);
            }
        }
        return "없음";
    }
}
