package christmas.domain;

import java.time.LocalDate;

public class SpecialDiscountCreator implements TotalDiscountCreator {

    @Override
    public TotalDiscount from(LocalDate date) {
        TotalDiscountType type = TotalDiscountType.findSpecialTypeByLocalDate(date);

        if (type == TotalDiscountType.NONE) {
            return TotalDiscount.NONE;
        }

        return new TotalDiscount(new Money(1000), type);
    }
}
