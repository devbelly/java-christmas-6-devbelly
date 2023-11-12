package christmas.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum TotalDiscountType {
    CHRISTMAS_D_DAY(IntStream.range(1, 26).boxed().toList(),
        IntStream.range(1, 26).boxed().toList()),
    SPECIAL(List.of(3, 10, 17, 24, 25, 31), IntStream.range(1, 31 + 1).boxed().toList()),
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

    public static TotalDiscountType findSpecialTypeByLocalDate(LocalDate localDate) {
        int day = localDate.getDayOfMonth();

        return Stream.of(TotalDiscountType.SPECIAL)
            .filter(specialType -> specialType.periods.contains(day))
            .filter(specialType -> specialType.days.contains(day))
            .findAny()
            .orElse(TotalDiscountType.NONE);
    }
}
