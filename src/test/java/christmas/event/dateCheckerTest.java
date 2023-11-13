package christmas.event;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class dateCheckerTest extends NsTest {

    private final List<Integer> weekendList = new ArrayList<>(Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29,30));
    private final List<Integer> weekdayList = new ArrayList<>();

    // 인스턴스 초기화 블록
    {
        for (int day = 1; day <= 31; day++) {
            if (!weekendList.contains(day)) {
                weekdayList.add(day);
            }
        }
    }

    @Test
    void 평일이_맞는지_확인() {
        for(Integer day : weekdayList){
            assertSimpleTest(()-> assertFalse(dateChecker.isWeekend(day)));
        }

    }

    @Test
    void 주말이_맞는지_확인() {
        for(Integer day : weekendList){
            assertSimpleTest(()-> assertTrue(dateChecker.isWeekend(day)));
        }

    }

    @Test
    void 범위에_맞지_않으면_에러_발생_테스트() {
        assertThrows(IllegalArgumentException.class,() -> {
            dateChecker.isWeekend(35);
        });
        assertThrows(IllegalArgumentException.class,() -> {
            dateChecker.isWeekend(0);
        });
        }




    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
