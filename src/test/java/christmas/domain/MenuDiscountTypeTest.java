package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuDiscountTypeTest {

    @DisplayName("일요일, 월요일, 화요일, 수요일, 목요일은 평일 할인이 적용된다.")
    @ParameterizedTest
    @MethodSource("parametersForWeekDays")
    void findMenuDiscountTypeByWeekDays(LocalDate date) {
        assertThat(MenuDiscountType.findByLocalDate(date)).isEqualTo(MenuDiscountType.WEEKDAYS);
    }

    static Stream<Arguments> parametersForWeekDays() {
        return Stream.of(
            Arguments.of(LocalDate.of(2023, 12, 3)),
            Arguments.of(LocalDate.of(2023, 12, 4)),
            Arguments.of(LocalDate.of(2023, 12, 5)),
            Arguments.of(LocalDate.of(2023, 12, 6)),
            Arguments.of(LocalDate.of(2023, 12, 7))
        );
    }


    @DisplayName("금요일, 토요일은 주말 할인이 적용된다.")
    @ParameterizedTest
    @MethodSource("parametersForWeekends")
    void findMenuDiscountTypeByWeekends(LocalDate date) {
        assertThat(MenuDiscountType.findByLocalDate(date)).isEqualTo(MenuDiscountType.WEEKENDS);
    }

    static Stream<Arguments> parametersForWeekends() {
        return Stream.of(
            Arguments.of(LocalDate.of(2023, 12, 1)),
            Arguments.of(LocalDate.of(2023, 12, 2))
        );
    }
}