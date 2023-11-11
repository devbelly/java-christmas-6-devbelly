package christmas.domain;

public class OrderLine {

    private MenuDetail menuDetail;
    private int quantity;

    public Money getAmounts() {
        return menuDetail.calculateAmounts(quantity);
    }
}