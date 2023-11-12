package christmas.domain;

import static christmas.domain.MenuDiscountType.WEEKDAYS;
import static christmas.domain.MenuDiscountType.WEEKENDS;

import java.time.LocalDate;

public class MenuDiscount {

    private Money money;
    private MenuDiscountType type;

    private MenuDiscount(Money money, MenuDiscountType type) {
        this.money = money;
        this.type = type;
    }

    private static final MenuDiscount WEEKDAYS_DISCOUNT =
        new MenuDiscount(new Money(2023), MenuDiscountType.WEEKDAYS);

    private static final MenuDiscount WEEKENDS_DISCOUNT =
        new MenuDiscount(new Money(2023), MenuDiscountType.WEEKENDS);

    private static final MenuDiscount NONE = new MenuDiscount(Money.ZERO, MenuDiscountType.NONE);

    public static MenuDiscount of(OrderLine orderLine, LocalDate localDate) {
        MenuDiscountType type = MenuDiscountType.findByLocalDate(localDate);

        if (type == WEEKDAYS && orderLine.isDessert()) {
            return WEEKDAYS_DISCOUNT;
        }
        if (type == WEEKENDS && orderLine.isMain()) {
            return WEEKENDS_DISCOUNT;
        }
        return NONE;
    }
}
