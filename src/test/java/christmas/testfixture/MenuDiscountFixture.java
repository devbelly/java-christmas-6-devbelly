package christmas.testfixture;

import christmas.domain.MenuDetail;
import christmas.domain.MenuDiscount;
import christmas.domain.OrderLine;
import java.time.LocalDate;

public class MenuDiscountFixture {

    public static class Builder {

        private MenuDetail menuDetail = MenuDetail.T_BONE_STEAK;
        private int quantity = 1;
        private LocalDate localDate = LocalDate.of(2023, 12, 1);

        public Builder menuDetail(MenuDetail menuDetail) {
            this.menuDetail = menuDetail;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder localDate(LocalDate localDate) {
            this.localDate = localDate;
            return this;
        }

        public MenuDiscount build() {
            OrderLine orderLine = new OrderLine(menuDetail, quantity);
            return MenuDiscount.of(orderLine, localDate);
        }
    }
}
