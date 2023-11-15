package christmas.dto;

public class PriceResult {
    // 방문일, 원가, 할인가, 증정품 여부
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
    // 원가를 불러오는 메서드
    public Integer getOriginalPrice(){
        return originalPrice;
    }
    // 할인가를 불러오는 메서드
    public Integer getDiscountedPrice(){
        return discountedPrice;
    }
    // 증정품 수여 여부를 불러오는 메서드
    public Boolean getGift() { return isGiftEligible; }
}
