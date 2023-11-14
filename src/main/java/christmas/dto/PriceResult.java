package christmas.dto;

public class PriceResult {
    private final Integer date;
    private final Integer originalPrice;
    private final Integer discountedPrice;
    private final boolean isGiftEligible;

    public PriceResult( Integer date, Integer originalPrice, Integer discountedPrice, boolean isGiftEligible){
        this.date = date;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.isGiftEligible = isGiftEligible;
    }


    public Integer getDate() {return date;}
    public Integer getOriginalPrice(){
        return originalPrice;
    }
    public Integer getDiscountedPrice(){
        return discountedPrice;
    }
    public Boolean getGift() { return isGiftEligible; }
}
