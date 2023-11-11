package christmas.domain;

public class OrderLine {

    private static final String INSUFFICIENT_QUANTITY_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private MenuDetail menuDetail;
    private int quantity;

    public OrderLine(MenuDetail menuDetail, int quantity) {
        validateAtLeastOneQuantity(quantity);
        this.menuDetail = menuDetail;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getAmounts() {
        return menuDetail.calculateAmounts(quantity);
    }

    public boolean isDrink() {
        return MenuGroup.findByMenuDetail(menuDetail) == MenuGroup.DRINK;
    }

    private void validateAtLeastOneQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(INSUFFICIENT_QUANTITY_ERROR_MESSAGE);
        }
    }
}