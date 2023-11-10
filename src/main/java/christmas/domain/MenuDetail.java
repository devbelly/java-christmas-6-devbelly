package christmas.domain;

public enum MenuDetail {
    MUSHROOM_SOUP("양송이수프", new Money(6000)),
    TAPAS("타파스", new Money(5500)),
    CAESAR_SALAD("시저샐러드", new Money(8000)),
    T_BONE_STEAK("T본스테이크", new Money(55000)),
    BARBECUE_RIBS("바비큐립", new Money(54000)),
    SEAFOOD_PASTA("해산물파스타", new Money(35000)),
    CHRISTMAS_PASTA("크리스마스파스타", new Money(25000)),
    CHOCOLATE_CAKE("초코케이크", new Money(15000)),
    ICE_CREAM("아이스크림", new Money(5000)),
    ZERO_COLA("제로콜라", new Money(3000)),
    RED_WINE("레드와인", new Money(60000)),
    CHAMPAGNE("샴페인", new Money(25000));

    private String title;
    private Money price;

    MenuDetail(String title, Money price) {
        this.title = title;
        this.price = price;
    }
}