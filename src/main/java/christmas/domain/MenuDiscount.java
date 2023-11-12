package christmas.domain;

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

    private static final MenuDiscount NONE = new MenuDiscount(Money.ZERO, MenuDiscountType.NONE);

    public static MenuDiscount of(OrderLine orderLine, LocalDate localDate) {
        MenuDiscountType type = MenuDiscountType.findByLocalDate(localDate);

        if (type == MenuDiscountType.WEEKDAYS && orderLine.isDessert()) {
            return WEEKDAYS_DISCOUNT;
        }
        return NONE;
    }
}
