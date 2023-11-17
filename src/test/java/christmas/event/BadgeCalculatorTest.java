package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BadgeCalculatorTest {

    @Test
    public void 증정품을_받고_뱃지를_받는경우() {
        int originalPrice = 120000;
        int discountedPrice = 110000;
        boolean isGiftEligible = true;

        String expectedBadge = "산타";
        String actualBadge = BadgeCalculator.badgeCalculator(originalPrice, discountedPrice, isGiftEligible);

        assertEquals(expectedBadge, actualBadge);
    }

    @Test
    public void 증정품을_받지_않고_뱃지를_받는경우() {
        int originalPrice = 30000;
        int discountedPrice = 20000;
        boolean isGiftEligible = false;

        String expectedBadge = "트리";
        String actualBadge = BadgeCalculator.badgeCalculator(originalPrice, discountedPrice, isGiftEligible);

        assertEquals(expectedBadge, actualBadge);
    }

    @Test
    public void 뱃지를_못_받는_경우() {
        int originalPrice = 15000;
        int discountedPrice = 14000;
        boolean isGiftEligible = false;

        String expectedBadge = "없음";
        String actualBadge = BadgeCalculator.badgeCalculator(originalPrice, discountedPrice, isGiftEligible);

        assertEquals(expectedBadge, actualBadge);
    }

    // 추가적인 테스트 케이스는 여기에 구현
}
