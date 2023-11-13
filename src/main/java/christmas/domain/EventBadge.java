package christmas.domain;

public enum EventBadge {
    NONE(0), STAR(5000), TREE(10000), SANTA(20000);

    private final int money;

    EventBadge(int money) {
        this.money = money;
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
}
