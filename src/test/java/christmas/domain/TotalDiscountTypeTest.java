package christmas.domain;

import static christmas.domain.TotalDiscountType.findSpecialTypeByLocalDate;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TotalDiscountTypeTest {

    @DisplayName("특별 할인은 3, 10, 17, 24, 25, 31일에 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void findSpecialTypeByValidDay(int days) {
        LocalDate date = LocalDate.of(2023, 12, days);

        assertThat(findSpecialTypeByLocalDate(date))
            .isEqualTo(TotalDiscountType.SPECIAL);
    }

    @DisplayName("나머지 날에 대해선 특별 할인이 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12})
    void findSpecialTypeByInvalidDay(int days) {
        LocalDate date = LocalDate.of(2023, 12, days);

        assertThat(findSpecialTypeByLocalDate(date))
            .isEqualTo(TotalDiscountType.NONE);
    }
}