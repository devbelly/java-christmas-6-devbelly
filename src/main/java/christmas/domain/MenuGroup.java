package christmas.domain;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {
    APPETIZER("애피타이저",
        Arrays.asList(MenuDetail.MUSHROOM_SOUP, MenuDetail.TAPAS, MenuDetail.CAESAR_SALAD)),
    MAIN("메인", List.of()), DESSERT("디저트", List.of()), DRINK("음료", List.of());

    private String title;
    private List<MenuDetail> menuDetails;

    MenuGroup(String title, List<MenuDetail> menuDetails) {
        this.title = title;
        this.menuDetails = menuDetails;
    }
}
