package christmas.domain;

import java.util.List;

public class Order {

    private static final String INSUFFICIENT_ORDERLINE_ERROR_MESSAGE = "[ERROR] 주문 항목이 비어 있습니다.";

    private List<OrderLine> orderLines;

    public Order(List<OrderLine> orderLines) {
        validateAtLeastOneOrderLine(orderLines);
        this.orderLines = orderLines;
    }

    private void validateAtLeastOneOrderLine(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException(INSUFFICIENT_ORDERLINE_ERROR_MESSAGE);
        }
    }
}
