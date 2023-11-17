package christmas.dto;

import christmas.data.Foods.FoodItem;
import java.util.HashMap;
import java.util.Map;

public class OrderList {
    // 주문 항목을 저장하는 맵
    private Map<String, Integer> items;
    // OrderList 객체 생성 시 빈 맵을 초기화
    public OrderList(){
        this.items = new HashMap<>();
    }
    // 메뉴의 수량을 주문에 추가하는 메서드
    public void addItem(String menu, Integer quantity){
        this.items.put(menu, quantity);
    }
    // 주문 항목을 불변한 새 맵으로 복제하여 반환하는 메서드
    public Map<String, Integer> getItems() {
        return new HashMap<>(items);
    }
    // 주문 항목이 음료수만 포함하는지 확인하는 메서드
    public boolean isBeverageOnly() {
        for (String menuItem : items.keySet()) {
            String category = FoodItem.getTypeOfMenu(menuItem);
            if (!"Beverage".equals(category)) {
                return false;
            }
        }
        return true;
    }

}
