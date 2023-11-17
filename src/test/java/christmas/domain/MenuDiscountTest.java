package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.testfixture.LocalDateFixture;
import christmas.testfixture.MenuDiscountFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class MenuDiscountTest {

    private MenuDiscountFixture.Builder builder = new MenuDiscountFixture.Builder();

    @DisplayName("에피타이저는 메뉴 할인이 적용되지 않는다.")
    @ParameterizedTest
    @EnumSource(mode = Mode.INCLUDE, names = {"MUSHROOM_SOUP", "TAPAS", "CAESAR_SALAD"})
    void createAppetize(MenuDetail menuDetail) {
        MenuDiscount appetizer = builder
            .menuDetail(menuDetail)
            .localDate(LocalDateFixture.MONDAY)
            .build();

        assertThat(appetizer.getMoney()).isEqualTo(Money.ZERO);
    }

    @DisplayName("메인 메뉴는 주말 할인이 적용된다.")
    @ParameterizedTest
    @EnumSource(mode = Mode.INCLUDE, names = {"T_BONE_STEAK", "BARBECUE_RIBS", "SEAFOOD_PASTA",
        "CHRISTMAS_PASTA"})
    void createMenuDiscountByMain(MenuDetail menuDetail) {
        MenuDiscount mainMenus = builder
            .menuDetail(menuDetail)
            .localDate(LocalDateFixture.FRIDAY)
            .build();

        assertThat(mainMenus.getMoney()).isEqualTo(new Money(2023));
    }

    @DisplayName("디저트는 평일 할인이 적용된다.")
    @ParameterizedTest
    @EnumSource(mode = Mode.INCLUDE, names = {"CHOCOLATE_CAKE", "ICE_CREAM"})
    void createMenuDiscountByDessert(MenuDetail menuDetail) {
        MenuDiscount desserts = builder
            .menuDetail(menuDetail)
            .localDate(LocalDateFixture.MONDAY)
            .build();

        assertThat(desserts.getMoney()).isEqualTo(new Money(2023));
    }

    @DisplayName("음료는 메뉴 할인이 적용되지 않는다.")
    @ParameterizedTest
    @EnumSource(mode = Mode.INCLUDE, names = {"ZERO_COLA", "RED_WINE", "CHAMPAGNE"})
    void createMenuDiscountByDrinks(MenuDetail menuDetail) {
        MenuDiscount drinks = builder
            .menuDetail(menuDetail)
            .localDate(LocalDateFixture.MONDAY)
            .build();

        assertThat(drinks.getMoney()).isEqualTo(Money.ZERO);
    }
}