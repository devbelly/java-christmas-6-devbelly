package christmas.domain;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.IntStream;

public enum MenuDiscountType {

    WEEKDAYS(IntStream.range(1, 31 + 1).boxed().toList(),
        List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY)),
    WEEKENDS(List.of(), List.of()),
    NONE(List.of(), List.of());

    List<Integer> days;
    List<DayOfWeek> dayOfWeeks;

    MenuDiscountType(List<Integer> days, List<DayOfWeek> dayOfWeeks) {
        this.days = days;
        this.dayOfWeeks = dayOfWeeks;
    }
}
