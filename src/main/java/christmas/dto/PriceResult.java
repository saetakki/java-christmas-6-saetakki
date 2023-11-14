package christmas.dto;

public class PriceResult {
    private final Integer date;
    private final Integer originalPrice;
    private final Integer discountedPrice;
    private final Integer includeGiftPrice;

    public PriceResult( Integer date, Integer originalPrice, Integer discountedPrice, Integer includeGiftPrice){
        this.date = date;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.includeGiftPrice = includeGiftPrice;
    }


    public Integer getDate() {return date;}
    public Integer getOriginalPrice(){
        return originalPrice;
    }
    public Integer getDiscountedPrice(){
        return discountedPrice;
    }
    public Integer getIncludeGiftPrice(){ return includeGiftPrice;}
}
