package christmas.data;
import static christmas.data.Foods.FoodItem.getPriceOfMenu;
import static christmas.data.Foods.FoodItem.isExistMenu;
import static christmas.data.Foods.FoodItem.isMainCourseOrDessert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.Test;

public class FoodsTest extends NsTest {





    @Test
    void 메뉴_존재여부_검출_테스트(){
        String[] foodItemsToTest = {
                "양송이수프",  // true
                "타파스",      // true
                "티본스테이크",  // true
                "초코케이크",   // true
                "레드와인",    // true
                "환타",       // false
                "피자",       // false
                "초밥",       // false
                "마카롱"      // false
        };

        Boolean[] expectedResults ={true, true, true, true, true, false, false, false, false};

        for(int i = 0; i<foodItemsToTest.length;i++){
            assertEquals(isExistMenu(foodItemsToTest[i]),expectedResults[i]);
        }
    }

    @Test
    void 메인_혹은_디저트검사_테스트(){
        String[] foodItemsToTest = {
                "티본스테이크",  // MainCourse
                "바비큐립",      // MainCourse
                "해산물파스타",   // MainCourse
                "크리스마스파스타", // MainCourse
                "초코케이크",     // Dessert
                "아이스크림",     // Dessert
                "제로콜라",      // 아님
                "레드와인",      // 아님
                "샴페인",        // 아님
                "햄버거",       // 없음
                "사과파이"      // 없음
        };
        boolean[] expectedResults = {
                true,  // 티본스테이크
                true,  // 바비큐립
                true,  // 해산물파스타
                true,  // 크리스마스파스타
                true,  // 초코케이크
                true,  // 아이스크림
                false, // 제로콜라
                false, // 레드와인
                false, // 샴페인
                false, // 햄버거
                false  // 사과파이
        };

        for (int i =0; i<foodItemsToTest.length; i++){
            assertEquals(isMainCourseOrDessert(foodItemsToTest[i]), expectedResults[i]);
        }


    }

    @Test
    void 가격_불러오기_기능_테스트(){
        String[] foodItemToTest = {
                "양송이수프",
                "티본스테이크",
                "초코케이크",
                "제로콜라",
        };
        Integer[] expectedResult = {6000,55000,15000,3000 };

        for (int i=0; i<foodItemToTest.length;i++){
            assertEquals(getPriceOfMenu(foodItemToTest[i]),expectedResult[i]);
        }

    }




    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
