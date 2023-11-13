package christmas.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum PresentItem {
    NONE("없음", 0, 0),
    CHAMPAGNE("샴페인", 120000, 25000);
    private String title;
    private int thresholdPrice;
    private int price;

    PresentItem(String title, int thresholdPrice, int price) {
        this.title = title;
        this.thresholdPrice = thresholdPrice;
        this.price = price;
    }

    public static PresentItem findByTotalPrice(int thresholdPrice) {
        return Arrays.stream(PresentItem.values())
            .filter(item -> item.thresholdPrice <= thresholdPrice)
            .max(Comparator.comparingInt(item -> item.thresholdPrice))
            .orElse(NONE);
    }

    public int getPrice() {
        return price;
    }
}
