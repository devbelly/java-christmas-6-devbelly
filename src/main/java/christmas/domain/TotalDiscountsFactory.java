package christmas.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalDiscountsFactory {

    private static final Map<TotalDiscountType, TotalDiscountCreator> creators = new HashMap<>();

    static {
        creators.put(TotalDiscountType.CHRISTMAS_D_DAY, new ChristmasDDayDiscountCreator());
        creators.put(TotalDiscountType.SPECIAL, new SpecialDiscountCreator());
    }

    public static TotalDiscounts create(LocalDate date) {

        List<TotalDiscount> totalDiscounts = List.of(
            createTotalDiscount(TotalDiscountType.CHRISTMAS_D_DAY, date),
            createTotalDiscount(TotalDiscountType.SPECIAL, date)
        );

        return new TotalDiscounts(totalDiscounts);
    }

    private static TotalDiscount createTotalDiscount(TotalDiscountType type, LocalDate date) {
        return creators.get(type).from(date);
    }
}