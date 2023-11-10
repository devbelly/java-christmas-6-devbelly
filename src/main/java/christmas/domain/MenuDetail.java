package christmas.domain;

public enum MenuDetail {
    MUSHROOM_SOUP("양송이수프", new Money(6000)),
    TAPAS("타파스", new Money(5500)),
    CAESAR_SALAD("시저샐러드", new Money(8000));

    private String title;
    private Money price;

    MenuDetail(String title, Money price) {
        this.title = title;
        this.price = price;
    }
}