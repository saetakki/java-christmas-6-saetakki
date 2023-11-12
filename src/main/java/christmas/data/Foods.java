package christmas.data;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Foods {

    public enum FoodItem {
        양송이수프("양송이수프", "Appetizer", 6000),
        타파스("타파스", "Appetizer", 5500),
        시저샐러드("시저샐러드", "Appetizer", 8000),
        티본스테이크("티본스테이크", "MainCourse", 55000),
        바비큐립("바비큐립", "MainCourse", 54000),
        해산물파스타("해산물파스타", "MainCourse", 35000),
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

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public Integer getPrice() {
            return price;
        }

        private static final Map<String, FoodItem> FOOD_NAME_MAP;

        static {
            Map<String, FoodItem> map = new HashMap<>();
            for (FoodItem item : FoodItem.values()){
                map.put(item.getName(), item);
            }
            FOOD_NAME_MAP = Collections.unmodifiableMap(map);
        }

        public static boolean isExistMenu(String menu){
            return FOOD_NAME_MAP.containsKey(menu);
        }

        public static boolean isMainCourseOrDessert(String name){
            FoodItem foodItem = FOOD_NAME_MAP.get(name);
            return foodItem != null && ("MainCourse".equals(foodItem.getType()) || "Dessert".equals(foodItem.getType()));
        }

        public static Integer getPriceOfMenu(String name){
            FoodItem foodItem = FOOD_NAME_MAP.get(name);
            if (foodItem==null){
                return -1;
            }
            return foodItem.getPrice();
        }


    }

}
