package christmas.domain;

import java.util.List;

public class TotalDiscounts {

    private List<TotalDiscount> totalDiscounts;

    public TotalDiscounts(List<TotalDiscount> totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public List<TotalDiscount> getTotalDiscounts() {
        return totalDiscounts;
    }

    public Money sumOfSpecialDiscounts() {
        return totalDiscounts.stream()
            .filter(TotalDiscount::isSpecial)
            .map(TotalDiscount::getMoney)
            .reduce(Money.ZERO, Money::plus);
    }

    public Money sumOfChristmasDiscounts() {
        return totalDiscounts.stream()
            .filter(TotalDiscount::isChristmasDDay)
            .map(TotalDiscount::getMoney)
            .reduce(Money.ZERO, Money::plus);
    }
}
