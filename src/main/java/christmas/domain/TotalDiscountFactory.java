package christmas.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TotalDiscountFactory {

    private static final Map<TotalDiscountType, TotalDiscountCreator> creators = new HashMap<>();

    static {
        creators.put(TotalDiscountType.CHRISTMAS_D_DAY, new ChristmasDDayDiscountCreator());
    }

    public static TotalDiscount createTotalDiscount(TotalDiscountType type, LocalDate date) {
        return creators.get(type).from(date);
    }
}