package christmas.dto;

import java.util.HashMap;
import java.util.Map;

public class OrderList {
    private Map<String, Integer> items;
    public OrderList(){
        this.items = new HashMap<>();
    }

    public void addItem(String menu, Integer quantity){
        this.items.put(menu, quantity);
    }

    public Map<String, Integer> getItems() {
        return new HashMap<>(items);
    }

}
