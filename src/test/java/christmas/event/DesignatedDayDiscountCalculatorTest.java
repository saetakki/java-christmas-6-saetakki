package christmas.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import java.util.List;
import org.junit.jupiter.api.Test;

class DesignatedDayDiscountCalculatorTest extends NsTest {


    @Test
    void 크리스마스_당일까지_할인_적용_검사(){
        List<Integer> dateList = Randoms.pickUniqueNumbersInRange(1, 25, 10);

        for(Integer date: dateList){
            int expectedDiscountAmount = 1000 + (date-1)*100;
            int actualDiscountAmount = DesignatedDayDiscountCalculator.applyDailyDiscount(0, date);
            assertEquals(expectedDiscountAmount+actualDiscountAmount, 0);
        }
    }
    @Test
    void 크리스마스_이후_할인_미적용_검사(){
        List<Integer> dateList = Randoms.pickUniqueNumbersInRange(26, 31, 6);
        int TotalPrice = 10000;
        for(Integer date: dateList){
            int actualDiscountAmount = DesignatedDayDiscountCalculator.applyDailyDiscount(TotalPrice, date);
            assertEquals(actualDiscountAmount, 10000);
        }
    }
    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
