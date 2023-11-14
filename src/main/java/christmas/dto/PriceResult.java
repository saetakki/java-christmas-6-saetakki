package christmas.dto;

public class PriceResult {
    private final Integer originalPrice;
    private final Integer discountedPrice;
    private final Integer includeGiftPrice;

    public PriceResult(Integer originalPrice, Integer discountedPrice, Integer includeGiftPrice){
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.includeGiftPrice = includeGiftPrice;
    }

    public Integer getOriginalPrice(){
        return originalPrice;
    }
    public Integer getDiscountedPrice(){
        return discountedPrice;
    }
    public Integer getIncludeGiftPrice(){ return includeGiftPrice;}
}
