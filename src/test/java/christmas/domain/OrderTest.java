package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("음료만 주문할 경우 예외가 발생한다")
    @Test
    void createOrderByOnlyDrinks() {
        List<OrderLine> orderLines = List.of(
            new OrderLine(MenuDetail.ZERO_COLA, 3),
            new OrderLine(MenuDetail.CHAMPAGNE, 1)
        );

        assertThatThrownBy(() -> new Order(orderLines))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 음료만 주문할 수 없습니다.");
    }

    @DisplayName("총 주문 금액은 각 메뉴의 구매 가격 합을 모두 더한 금액이다.")
    @Test
    void getTotalPrice() {
        List<OrderLine> orderLines = List.of(
            new OrderLine(MenuDetail.MUSHROOM_SOUP, 5),
            new OrderLine(MenuDetail.T_BONE_STEAK, 6),
            new OrderLine(MenuDetail.CHRISTMAS_PASTA, 7)
        );

        Order order = new Order(orderLines);

        assertThat(order.getTotalAmounts()).isEqualTo(
            new Money(6_000 * 5 + 55_000 * 6 + 25_000 * 7));
    }
}