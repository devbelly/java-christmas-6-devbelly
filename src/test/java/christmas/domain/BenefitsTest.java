package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.testfixture.LocalDateFixture;
import christmas.testfixture.MenuDiscountFixture;
import christmas.testfixture.MenuDiscountFixture.Builder;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BenefitsTest {

    private MenuDiscountFixture.Builder builder = new Builder();
    private Benefits benefits;

    @BeforeEach
    void setUp() {
        benefits = new Benefits();
    }

    @DisplayName("주말 메뉴 할인이 적용 되었을 때, 할인 금액을 반환한다.")
    @Test
    void getDiscountAmountsWhenWeekendDiscountApply() {
        List<MenuDiscount> menuDiscounts =
            List.of(builder.menuDetail(MenuDetail.T_BONE_STEAK).quantity(3).localDate(
                LocalDateFixture.FRIDAY).build());

        benefits.updateMenuDiscounts(new MenuDiscounts(menuDiscounts));

        assertThat(benefits.getDiscountsAmounts()).isEqualTo(new Money(6069));
    }

    @DisplayName("평일 메뉴 할인이 적용 되었을 때, 할인 금액을 반환한다.")
    @Test
    void getDiscountAmountsWhenWeekdayDiscountApply() {
        List<MenuDiscount> menuDiscounts =
            List.of(builder.menuDetail(MenuDetail.CHOCOLATE_CAKE).quantity(2).localDate(
                LocalDateFixture.MONDAY).build());

        benefits.updateMenuDiscounts(new MenuDiscounts(menuDiscounts));

        assertThat(benefits.getDiscountsAmounts()).isEqualTo(new Money(4046));
        assertThat(benefits.getDiscountsAmounts()).isEqualTo(new Money(4046));
    }

    @DisplayName("특별 할인이 적용 되었을 때, 할인 금액을 반환한다.")
    @Test
    void getTotalDiscountAmounts() {
        TotalDiscounts discounts = TotalDiscountsFactory.create(LocalDate.of(2023, 12, 25));

        benefits.updateTotalDiscounts(discounts);

        assertThat(benefits.getDiscountsAmounts()).isEqualTo(new Money(4400));
        assertThat(benefits.getTotalBenefitsAmounts()).isEqualTo(new Money(4400));
    }

    @DisplayName("총주문 금액에 따라 증정 품목을 반환한다.")
    @ParameterizedTest
    @MethodSource("parametersForGetPresent")
    void getPresent(List<OrderLine> orderLines, PresentItem presentItem) {
        Order order = new Order(orderLines);

        benefits.updatePresent(order);

        assertThat(benefits.getPresent()).isEqualTo(presentItem);
        assertThat(benefits.getTotalBenefitsAmounts()).isEqualTo(new Money(presentItem.getPrice()));
    }

    static Stream<Arguments> parametersForGetPresent() {
        return Stream.of(
            Arguments.of(List.of(
                new OrderLine(MenuDetail.T_BONE_STEAK, 2),
                new OrderLine(MenuDetail.ICE_CREAM, 2)
            ), PresentItem.CHAMPAGNE),
            Arguments.of(List.of(
                new OrderLine(MenuDetail.T_BONE_STEAK, 2),
                new OrderLine(MenuDetail.ICE_CREAM, 1)
            ), PresentItem.NONE)
        );
    }
}