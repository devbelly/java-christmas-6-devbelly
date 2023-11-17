package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

    @DisplayName("메인 메뉴라면 isMain은 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("parametersForMain")
    void createOrderLineWithMain(MenuDetail mainDish) {
        OrderLine orderLine = new OrderLine(mainDish, 1);
        assertThat(orderLine.isMain()).isTrue();
    }

    static Stream<Arguments> parametersForMain() {
        return Stream.of(
            Arguments.of(MenuDetail.T_BONE_STEAK),
            Arguments.of(MenuDetail.BARBECUE_RIBS),
            Arguments.of(MenuDetail.SEAFOOD_PASTA),
            Arguments.of(MenuDetail.CHRISTMAS_PASTA)
        );
    }

    @DisplayName("음료라면 isDrink는 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("parametersForDrink")
    void createOrderLineWithDrink(MenuDetail drink) {
        OrderLine orderLine = new OrderLine(drink, 1);
        assertThat(orderLine.isDrink()).isTrue();
    }

    static Stream<Arguments> parametersForDrink() {
        return Stream.of(
            Arguments.of(MenuDetail.ZERO_COLA),
            Arguments.of(MenuDetail.RED_WINE),
            Arguments.of(MenuDetail.CHAMPAGNE)
        );
    }

    @DisplayName("디저트라면 isDessert는 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("parametersForDessert")
    void createOrderLineWithDessert(MenuDetail dessert) {
        OrderLine orderLine = new OrderLine(dessert, 1);
        assertThat(orderLine.isDessert()).isTrue();
    }

    static Stream<Arguments> parametersForDessert() {
        return Stream.of(
            Arguments.of(MenuDetail.CHOCOLATE_CAKE),
            Arguments.of(MenuDetail.ICE_CREAM)
        );
    }

    @DisplayName("메뉴에 대한 금액은 메뉴의 가격과 수량을 곱한 값이다.")
    @Test
    void test(){
        OrderLine orderLine = new OrderLine(MenuDetail.MUSHROOM_SOUP, 2);
        assertThat(orderLine.getAmounts()).isEqualTo(new Money(12000));
    }
}