package christmas.dtos;

import christmas.domain.MenuDetail;

public class OrderLineDto {

    private MenuDetail menuDetail;
    private int quantity;

    public OrderLineDto(MenuDetail menuDetail, int quantity) {
        this.menuDetail = menuDetail;
        this.quantity = quantity;
    }

    public MenuDetail getMenuDetail() {
        return menuDetail;
    }

    public int getQuantity() {
        return quantity;
    }
}
