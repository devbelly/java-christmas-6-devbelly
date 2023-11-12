package christmas.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public enum TotalDiscountType {
    CHRISTMAS_D_DAY(IntStream.range(1, 26).boxed().toList()), SPECIAL(List.of()), NONE(List.of());

    private List<Integer> days;

    TotalDiscountType(List<Integer> days) {
        this.days = days;
    }

    public List<Integer> getDays() {
        return Collections.unmodifiableList(days);
    }
}
