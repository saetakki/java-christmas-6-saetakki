package christmas.event;

import static christmas.Constants.BADGES;
import static christmas.Constants.GIFT;
import static christmas.Constants.GRADE_PRICE;
import static christmas.data.Foods.FoodItem.getPriceOfMenu;

public class BadgeCalculator {

    /*
    뱃지 종류를 결정하는 메서드
    뱃지는 혜택금액을 기준으로 결정하고 혜택 금액은 증정품의 유무에 따라서 가격이 달라짐
    isGiftEligible이라는 증정품 수령 여부를 가지고 gap에 증정품의 가격을 추가할지 말지를 결정
    그 후 뱃지 수여 금액을 역순으로 순회하면서 해당 수여 기준금액보다 gap이 더 크다면 해당 등급을 수여
    예를들어 gap이 3.2만이고 뱃지 최고등급 수여금액이 2만이라면 최고등급 뱃지를 수여
    gap이 1.2만이고 뱃지의 등급 수여구간의 역순이 2만 1.5만 1만이라면 gap이 뱃지 수여금액보다 커지는 시점은 1만이기에
    이 경우에는 1만에 해당하는 뱃지 수여
    만약 수여할 수 있는 뱃지가 없다면 "없음"출력
    */

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
