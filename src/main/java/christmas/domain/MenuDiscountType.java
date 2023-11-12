package christmas.domain;

import java.util.List;
import java.util.stream.IntStream;

public enum MenuDiscountType {

    WEEKDAYS(IntStream.range(1, 31 + 1).boxed().toList()), WEEKENDS(List.of()), NONE(List.of());

    List<Integer> days;

    MenuDiscountType(List<Integer> days) {
        this.days = days;
    }
}
