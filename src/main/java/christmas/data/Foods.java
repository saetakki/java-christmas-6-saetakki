package christmas.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Foods {

    // 판매하는 음식들을 담은 enum
    public enum FoodItem {
        양송이수프("양송이수프", "Appetizer", 6000),
        타파스("타파스", "Appetizer", 5500),
        시저샐러드("시저샐러드", "Appetizer", 8000),
        티본스테이크("티본스테이크", "MainCourse", 55000),
        바비큐립("바비큐립", "MainCourse", 54000),
        해산물파스타("해산물파스타", "MainCourse", 35000) ,
        크리스마스파스타("크리스마스파스타", "MainCourse", 25000),
        초코케이크("초코케이크", "Dessert", 15000),
        아이스크림("아이스크림", "Dessert", 5000),
        제로콜라("제로콜라", "Beverage", 3000),
        레드와인("레드와인", "Beverage", 60000),
        샴페인("샴페인", "Beverage", 25000);

        private final String name;
        private final String type;
        private final int price;

        FoodItem(String name, String type, int price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }
        // 음식 이름 반환
        public String getName() {
            return name;
        }
        // 음식 종류 반환
        public String getType() {
            return type;
        }
        // 음식 가격 반환
        public Integer getPrice() {
            return price;
        }
        // 음식 이름과 FoodItem 매핑을 가진 불변 Map 생성
        private static final Map<String, FoodItem> FOOD_NAME_MAP;

        static {
            Map<String, FoodItem> map = new HashMap<>();
            for (FoodItem item : FoodItem.values()){
                map.put(item.getName(), item);
            }
            FOOD_NAME_MAP = Collections.unmodifiableMap(map);
        }
        // 입력받은 음식이 판매하는 음식인지 확인하는 메서드
        public static boolean isExistMenu(String menu){
            return FOOD_NAME_MAP.containsKey(menu);
        }
        // 입력받은 음식이 메인코스 혹은 디저트인지 확인하는 메서드
        public static boolean isMainCourseOrDessert(String name){
            FoodItem foodItem = FOOD_NAME_MAP.get(name);
            return foodItem != null && ("MainCourse".equals(foodItem.getType()) || "Dessert".equals(foodItem.getType()));
        }
        // 입력받은 음식의 가격을 불러오는 메서드
        public static Integer getPriceOfMenu(String name){
            FoodItem foodItem = FOOD_NAME_MAP.get(name);
            if (foodItem==null){
                return -1;
            }
            return foodItem.getPrice();
        }
        // 입력받은 음식의 종류를 불러오는 메서드(eg. 제로콜라 -> Beverage)
        public static String getTypeOfMenu(String name) {
            FoodItem foodItem = FOOD_NAME_MAP.get(name);
            if (foodItem != null) {
                return foodItem.getType();
            }
            return "Unknown";
        }

    }

}
