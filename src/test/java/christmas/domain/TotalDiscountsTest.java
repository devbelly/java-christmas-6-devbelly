package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalDiscountsTest {

    private TotalDiscounts totalDiscounts;
    private TotalDiscount specialDiscount;
    private TotalDiscount christmasDDayDiscount;

    @BeforeEach
    void setUp() {
        specialDiscount =
            new TotalDiscount(new Money(1000), TotalDiscountType.SPECIAL);
        christmasDDayDiscount =
            new TotalDiscount(new Money(1900), TotalDiscountType.CHRISTMAS_D_DAY);
    }

    @DisplayName("특별 할인의 총합을 반환한다.")
    @Test
    void getSumOfSpecialDiscounts() {
        totalDiscounts = new TotalDiscounts(List.of(specialDiscount, christmasDDayDiscount));

        assertThat(totalDiscounts.sumOfSpecialDiscounts()).isEqualTo(new Money(1000));
    }

    @DisplayName("크리스마스 디데이 할인의 총합을 반환한다.")
    @Test
    void getSumOfChristmasDDayDiscounts() {
        totalDiscounts = new TotalDiscounts(List.of(specialDiscount, christmasDDayDiscount));

        assertThat(totalDiscounts.sumOfChristmasDiscounts()).isEqualTo(new Money(1900));
    }

    @DisplayName("할인이 없으면 0을 반환한다.")
    @Test
    void getZeroDiscounts() {
        totalDiscounts = new TotalDiscounts(List.of(TotalDiscount.NONE, TotalDiscount.NONE));

        assertThat(totalDiscounts.sumOfChristmasDiscounts()).isEqualTo(new Money(0));
        assertThat(totalDiscounts.sumOfSpecialDiscounts()).isEqualTo(new Money(0));
    }
}