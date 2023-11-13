package christmas.domain;

public class TotalDiscount {

    public static final TotalDiscount NONE = new TotalDiscount(Money.ZERO, TotalDiscountType.NONE);

    private Money money;
    private TotalDiscountType type;

    TotalDiscount(Money money, TotalDiscountType type) {
        this.money = money;
        this.type = type;
    }

    public boolean isSpecial() {
        return type == TotalDiscountType.SPECIAL;
    }

    public boolean isChristmasDDay() {
        return type == TotalDiscountType.CHRISTMAS_D_DAY;
    }

    public Money getMoney() {
        return money;
    }
}
