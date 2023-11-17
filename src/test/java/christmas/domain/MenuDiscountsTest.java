package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.testfixture.LocalDateFixture;
import christmas.testfixture.MenuDiscountFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuDiscountsTest {

    private MenuDiscountFixture.Builder builder = new MenuDiscountFixture.Builder();
    private MenuDiscounts menuDiscounts;

    @DisplayName("디저트 두개에 대한 평일 할인 총액을 반환한다.")
    @Test
    void createMenuDiscountWithDesserts() {
        List<MenuDiscount> desserts = List.of(
            builder
                .menuDetail(MenuDetail.CHOCOLATE_CAKE)
                .localDate(LocalDateFixture.MONDAY)
                .build(),
            builder
                .menuDetail(MenuDetail.CHOCOLATE_CAKE)
                .localDate(LocalDateFixture.TUESDAY)
                .build());

        menuDiscounts = new MenuDiscounts(desserts);

        assertThat(menuDiscounts.sumOfWeekDaysDiscounts()).isEqualTo(new Money(4046));
    }

    @DisplayName("평일 할인은 디저트만 해당한다.")
    @Test
    void createMenuDiscountWithDessertsAndSteak() {
        List<MenuDiscount> dessertsAndSteak = List.of(
            builder
                .menuDetail(MenuDetail.CHOCOLATE_CAKE)
                .localDate(LocalDateFixture.MONDAY)
                .build(),
            builder
                .menuDetail(MenuDetail.T_BONE_STEAK)
                .localDate(LocalDateFixture.TUESDAY)
                .build());

        menuDiscounts = new MenuDiscounts(dessertsAndSteak);

        assertThat(menuDiscounts.sumOfWeekDaysDiscounts()).isEqualTo(new Money(2023));
    }

    @DisplayName("메인 메뉴 두개에 대한 주말 할인 총액을 반환한다.")
    @Test
    void createMenuDiscountWithMainMenus() {
        List<MenuDiscount> mainMenus = List.of(
            builder
                .menuDetail(MenuDetail.T_BONE_STEAK)
                .localDate(LocalDateFixture.SATURDAY)
                .build(),
            builder
                .menuDetail(MenuDetail.SEAFOOD_PASTA)
                .localDate(LocalDateFixture.FRIDAY)
                .build());

        menuDiscounts = new MenuDiscounts(mainMenus);

        assertThat(menuDiscounts.sumOfWeekendsDiscounts()).isEqualTo(new Money(4046));
    }

    @DisplayName("주말 할인은 메인 메뉴만 해당한다.")
    @Test
    void createMenuDiscountWithMainMenusAndDessert() {
        List<MenuDiscount> mainMenusAndDessert = List.of(
            builder
                .menuDetail(MenuDetail.T_BONE_STEAK)
                .localDate(LocalDateFixture.SATURDAY)
                .build(),
            builder
                .menuDetail(MenuDetail.CHRISTMAS_PASTA)
                .localDate(LocalDateFixture.MONDAY)
                .build());

        menuDiscounts = new MenuDiscounts(mainMenusAndDessert);

        assertThat(menuDiscounts.sumOfWeekendsDiscounts()).isEqualTo(new Money(2023));
    }

    @DisplayName("각 메뉴당 할인을 적용한다.")
    @Test
    void createMenuDiscountWithSameMenu() {
        List<MenuDiscount> iceCreams = List.of(
            builder
                .menuDetail(MenuDetail.ICE_CREAM)
                .quantity(3)
                .localDate(LocalDateFixture.MONDAY)
                .build());

        menuDiscounts = new MenuDiscounts(iceCreams);

        assertThat(menuDiscounts.sumOfWeekDaysDiscounts()).isEqualTo(new Money(6069));
    }
}