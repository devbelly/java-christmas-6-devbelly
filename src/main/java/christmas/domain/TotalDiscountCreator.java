package christmas.domain;

import java.time.LocalDate;

public interface TotalDiscountCreator {

    TotalDiscount from(LocalDate date);
}
