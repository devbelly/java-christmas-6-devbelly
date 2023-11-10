package christmas.domain;

public enum MenuGroup {
    APPETIZER("애피타이저"), MAIN("메인"), DESSERT("디저트"), DRINK("음료");

    private String title;

    MenuGroup(String title) {
        this.title = title;
    }
}
