package christmas.domain;

import java.util.List;

public class MenuDiscounts {

    private static final MenuDiscounts NONE = new MenuDiscounts(List.of());

    private List<MenuDiscount> menuDiscounts;

    public MenuDiscounts(List<MenuDiscount> menuDiscounts) {
        this.menuDiscounts = menuDiscounts;
    }

    public Money sumOfWeekDaysDiscounts() {
        return menuDiscounts.stream()
            .filter(MenuDiscount::isWeekdays)
            .map(MenuDiscount::getMoney)
            .reduce(Money.ZERO, Money::plus);
    }
}
