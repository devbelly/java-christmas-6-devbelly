package christmas.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public enum TotalDiscountType {
    CHRISTMAS_D_DAY(IntStream.range(1, 26).boxed().toList(),
        IntStream.range(1, 26).boxed().toList()),
    SPECIAL(List.of(), IntStream.range(1, 31 + 1).boxed().toList()),
    NONE(List.of(), List.of());

    private List<Integer> days;
    private List<Integer> periods;

    TotalDiscountType(List<Integer> days, List<Integer> periods) {
        this.days = days;
        this.periods = periods;
    }

    public List<Integer> getDays() {
        return Collections.unmodifiableList(days);
    }
}
