package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주문한 메뉴가 20개를 초과하면 예외가 발생한다")
    @Test
    void createOrderWithTooManyMenuDetail() {
        List<OrderLine> orderLines = List.of(
            new OrderLine(MenuDetail.MUSHROOM_SOUP, 5),
            new OrderLine(MenuDetail.T_BONE_STEAK, 6),
            new OrderLine(MenuDetail.CHRISTMAS_PASTA, 7),
            new OrderLine(MenuDetail.ICE_CREAM, 3));

        assertThatThrownBy(() -> new Order(orderLines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }
}