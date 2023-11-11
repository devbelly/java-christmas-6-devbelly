package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderLineTest {

    @DisplayName("메뉴의 갯수가 1개보다 적으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0"})
    void createOrderLineByInsufficientQuantity(int quantity) {
        assertThatThrownBy(() -> new OrderLine(MenuDetail.MUSHROOM_SOUP, quantity))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }
}