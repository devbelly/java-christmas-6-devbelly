package christmas.domain;

import java.time.LocalDate;

public class BenefitCalculationFactory {

    private static final Money LEAST_DISCOUNT_AMOUNT = new Money(10000);

    public Benefits calculateBenefits(Order order, LocalDate date) {
        if (order.getTotalAmounts().isLessThan(LEAST_DISCOUNT_AMOUNT)) {
            return new Benefits();
        }
        Benefits benefits = new Benefits();

        TotalDiscounts totalDiscounts = TotalDiscountsFactory.create(date);

        benefits.updateTotalDiscounts(totalDiscounts);
        benefits.updatePresent(order);
        return benefits;
    }
}
