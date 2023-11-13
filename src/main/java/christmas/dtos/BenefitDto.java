package christmas.dtos;

import christmas.domain.BenefitType;

public class BenefitDto {

    private BenefitType benefitType;
    private int discountAmount;

    BenefitDto(BenefitType benefitType, int discountAmount) {
        this.benefitType = benefitType;
        this.discountAmount = discountAmount;
    }

    public static BenefitDto toDto(BenefitType benefitType, int discountAmount) {
        return new BenefitDto(benefitType, discountAmount);
    }

    public BenefitType getBenefitType() {
        return benefitType;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
