package christmas.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ChristmasDDayDiscountCreator implements TotalDiscountCreator {

    private static final Map<Integer, TotalDiscount> CACHE;

    static {
        CACHE = new HashMap<>();

        TotalDiscountType.CHRISTMAS_D_DAY.getDays()
            .forEach(day -> CACHE.put(day,
                new TotalDiscount(new Money(1000 + (day - 1) * 100),
                    TotalDiscountType.CHRISTMAS_D_DAY)));
    }

    @Override
    public TotalDiscount from(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();

        return CACHE.getOrDefault(dayOfMonth, TotalDiscount.NONE);
    }
}
