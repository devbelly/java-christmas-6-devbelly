package christmas.domain;

public class OrderLine {

    private MenuDetail menuDetail;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public Money getAmounts() {
        return menuDetail.calculateAmounts(quantity);
    }
}