package christmas.domain;

import java.util.List;

public class Order {

    private static final int MAX_QUANTITY = 20;
    private static final String INSUFFICIENT_ORDERLINE_ERROR_MESSAGE = "[ERROR] 주문 항목이 비어 있습니다.";
    private static final String TOO_MANY_ORDERLINE_QUANTITY_ERROR_MESSAGE = "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
    private static final String ONLY_DRINKS_ERROR_MESSAGE = "[ERROR] 음료만 주문할 수 없습니다.";

    private List<OrderLine> orderLines;

    public Order(List<OrderLine> orderLines) {
        validateAtLeastOneOrderLine(orderLines);
        validateTotalQuantity(orderLines);
        validateOnlyDrinks(orderLines);
        this.orderLines = orderLines;
    }

    public Money getTotalAmounts() {
        return orderLines.stream()
            .map(OrderLine::getAmounts)
            .reduce(Money::plus)
            .get();
    }

    private void validateAtLeastOneOrderLine(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException(INSUFFICIENT_ORDERLINE_ERROR_MESSAGE);
        }
    }

    private void validateTotalQuantity(List<OrderLine> orderLines) {
        int totalQuantity = orderLines.stream()
            .mapToInt(OrderLine::getQuantity)
            .sum();

        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(TOO_MANY_ORDERLINE_QUANTITY_ERROR_MESSAGE);
        }
    }

    private void validateOnlyDrinks(List<OrderLine> orderLines) {
        if (orderLines.stream().allMatch(OrderLine::isDrink)) {
            throw new IllegalArgumentException(ONLY_DRINKS_ERROR_MESSAGE);
        }
    }
}
