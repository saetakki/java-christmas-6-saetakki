package christmas.dto;

public class PriceResult {
    private final Integer originalPrice;
    private final Integer discountedPrice;

    public PriceResult(Integer originalPrice, Integer discountedPrice){
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }

    public Integer getOriginalPrice(){
        return originalPrice;
    }
    public Integer getDiscountedPrice(){
        return discountedPrice;
    }
}
