package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventBadgeTest {

    @DisplayName("총혜택 금액에 따라 배지를 부여한다")
    @ParameterizedTest
    @MethodSource("parametersForCreateEventBadgeByMoney")
    void createEventBadgeByMoney(int money, EventBadge badge) {
        assertThat(EventBadge.findByMoney(money)).isEqualTo(badge);
    }

    static Stream<Arguments> parametersForCreateEventBadgeByMoney() {
        return Stream.of(
            Arguments.of(4999, EventBadge.NONE),
            Arguments.of(5000, EventBadge.STAR),
            Arguments.of(9999, EventBadge.STAR),
            Arguments.of(10000, EventBadge.TREE),
            Arguments.of(19999, EventBadge.TREE),
            Arguments.of(20000, EventBadge.SANTA)
        );
    }
}