package christmas.domain;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {
    APPETIZER("애피타이저", Arrays.asList(
        MenuDetail.MUSHROOM_SOUP,
        MenuDetail.TAPAS,
        MenuDetail.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(
        MenuDetail.T_BONE_STEAK,
        MenuDetail.BARBECUE_RIBS,
        MenuDetail.SEAFOOD_PASTA,
        MenuDetail.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(
        MenuDetail.CHOCOLATE_CAKE,
        MenuDetail.ICE_CREAM
    )),
    DRINK("음료", Arrays.asList(
        MenuDetail.ZERO_COLA,
        MenuDetail.RED_WINE,
        MenuDetail.CHAMPAGNE
    ));

    private static final String MENU_GROUP_NOT_FOUND_ERROR_MESSAGE = "[ERROR] 메뉴를 확인해주세요.";

    private String title;
    private List<MenuDetail> menuDetails;

    MenuGroup(String title, List<MenuDetail> menuDetails) {
        this.title = title;
        this.menuDetails = menuDetails;
    }

    public static MenuGroup findByMenuDetail(MenuDetail menuDetail) {
        return Arrays.stream(MenuGroup.values())
            .filter(menuGroup -> menuGroup.hasMenuDetail(menuDetail))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(MENU_GROUP_NOT_FOUND_ERROR_MESSAGE));
    }

    public boolean hasMenuDetail(MenuDetail menuDetail) {
        return menuDetails.contains(menuDetail);
    }
}