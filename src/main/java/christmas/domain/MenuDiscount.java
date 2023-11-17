package christmas.domain;

import static christmas.domain.MenuDiscountType.WEEKDAYS;
import static christmas.domain.MenuDiscountType.WEEKENDS;

import java.time.LocalDate;

public class MenuDiscount {

    private static final int WEEKDAYS_DISCOUNT = 2023;
    private static final int WEEKENDS_DISCOUNT = 2023;
    private static final MenuDiscount NONE = new MenuDiscount(Money.ZERO, MenuDiscountType.NONE);
    private Money money;
    private MenuDiscountType type;

    private MenuDiscount(Money money, MenuDiscountType type) {
        this.money = money;
        this.type = type;
    }


    public static MenuDiscount of(OrderLine orderLine, LocalDate localDate) {
        MenuDiscountType type = MenuDiscountType.findByLocalDate(localDate);

        if (type == WEEKDAYS && orderLine.isDessert()) {
            return new MenuDiscount(
                new Money(WEEKDAYS_DISCOUNT).multiply(orderLine.getQuantity()), WEEKDAYS);
        }
        if (type == WEEKENDS && orderLine.isMain()) {
            return new MenuDiscount(
                new Money(WEEKENDS_DISCOUNT).multiply(orderLine.getQuantity()), WEEKENDS);
        }
        return NONE;
    }

    public boolean isWeekdays() {
        return type == WEEKDAYS;
    }

    public boolean isWeekends() {
        return type == WEEKENDS;
    }

    public Money getMoney() {
        return money;
    }
}
