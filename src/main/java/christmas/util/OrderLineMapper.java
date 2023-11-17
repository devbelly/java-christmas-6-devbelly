package christmas.util;

import christmas.domain.OrderLine;
import christmas.dtos.OrderLineDto;
import java.util.List;

public class OrderLineMapper {

    public static List<OrderLine> orderLineDtosToOrderLines(List<OrderLineDto> orderLineDtos) {
        return orderLineDtos.stream()
            .map(orderLineDto -> new OrderLine(
                orderLineDto.getMenuDetail(),
                orderLineDto.getQuantity())
            )
            .toList();
    }
}
