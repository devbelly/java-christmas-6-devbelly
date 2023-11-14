package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountCreatorTest {

    private SpecialDiscountCreator specialDiscountCreator;

    @BeforeEach
    void setUp() {
        specialDiscountCreator = new SpecialDiscountCreator();
    }

    @DisplayName("특별 할인 날짜를 입력하면 1000원을 할인하는 특별 할인 객체를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void createSpecialDiscountByValidDay(int day) {
        LocalDate localDate = LocalDate.of(2023, 12, day);

        assertThat(specialDiscountCreator.from(localDate))
            .usingRecursiveComparison()
            .isEqualTo(new TotalDiscount(new Money(1000), TotalDiscountType.SPECIAL));
    }

    @DisplayName("특별 할인 날짜가 아니라면 NONE 객체를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12})
    void createSpecialDiscountByInvalidDay(int day) {
        LocalDate localDate = LocalDate.of(2023, 12, day);

        assertThat(specialDiscountCreator.from(localDate))
            .usingRecursiveComparison()
            .isEqualTo(TotalDiscount.NONE);
    }
}