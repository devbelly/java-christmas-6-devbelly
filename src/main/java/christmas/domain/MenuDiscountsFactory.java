package christmas.domain;

import java.time.LocalDate;
import java.util.List;

public class MenuDiscountsFactory {

    private MenuDiscountsFactory() {
    }

    public static MenuDiscounts create(Order order, LocalDate date) {
        List<MenuDiscount> menuDiscounts = order.getOrderLines()
            .stream()
            .map(orderLine -> MenuDiscount.of(orderLine, date))
            .toList();

        return new MenuDiscounts(menuDiscounts);
    }
}
