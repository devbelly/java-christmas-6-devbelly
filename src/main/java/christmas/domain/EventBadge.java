package christmas.domain;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;

public enum EventBadge {
    NONE(0, "없음"), STAR(5000, "별"), TREE(10000, "트리"), SANTA(20000, "산타");

    private final int money;
    private String title;

    EventBadge(int money, String title) {
        this.money = money;
        this.title = title;
    }

    public static EventBadge findByMoney(int input) {
        return Arrays.stream(EventBadge.values())
            .filter(eventBadge -> eventBadge.money <= input)
            .max(comparingInt(o -> o.money))
            .orElse(NONE);
    }

    public String getTitle() {
        return title;
    }
}
