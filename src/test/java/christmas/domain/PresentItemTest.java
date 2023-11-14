package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PresentItemTest {

    @DisplayName("할인 전 총주문 금액이 12만원 이상이면 샴페인을 증정한다.")
    @ParameterizedTest
    @MethodSource("parametersForFindPresentItemByTotalPrice")
    void findPresentItemByTotalPrice(int threshold, PresentItem expected) {
        assertThat(PresentItem.findByTotalPrice(threshold)).isEqualTo(expected);
    }


    static Stream<Arguments> parametersForFindPresentItemByTotalPrice() {
        return Stream.of(
            Arguments.of(119999, PresentItem.NONE),
            Arguments.of(120000, PresentItem.CHAMPAGNE)
        );
    }

    @DisplayName("증정 선물의 가격을 통해 증정 선물을 찾는다.")
    @ParameterizedTest
    @MethodSource("parametersForFindPresentItemByPrice")
    void findPresentItemByPrice(int price, PresentItem expected) {
        assertThat(PresentItem.findByPresentPrice(price)).isEqualTo(expected);
    }

    static Stream<Arguments> parametersForFindPresentItemByPrice() {
        return Stream.of(
            Arguments.of(24999, PresentItem.NONE),
            Arguments.of(25000, PresentItem.CHAMPAGNE)
        );
    }
}