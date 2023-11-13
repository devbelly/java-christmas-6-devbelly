package christmas.domain;

public class BenefitCalculationFactory {

    private static final Money LEAST_DISCOUNT_AMOUNT = new Money(10000);

    public Benefits calculateBenefits(Order order) {
        if (order.getTotalAmounts().isLessThan(LEAST_DISCOUNT_AMOUNT)) {
            return new Benefits();
        }
        return new Benefits();
    }
}
