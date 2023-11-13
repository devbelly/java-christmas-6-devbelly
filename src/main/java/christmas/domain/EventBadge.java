package christmas.domain;

public enum EventBadge {
    NONE(0, "없음"), STAR(5000, "별"), TREE(10000, "트리"), SANTA(20000, "산타");

    private final int money;
    private String title;

    EventBadge(int money, String title) {

        this.money = money;
        this.title = title;
    }

    public static EventBadge findByMoney(int input) {
        if (input < 5000) {
            return NONE;
        }
        if (input < 10000) {
            return STAR;
        }
        if (input < 20000) {
            return TREE;
        }
        return SANTA;
    }

    public String getTitle() {
        return title;
    }
}
