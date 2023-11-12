package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
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

    public static MenuDiscountType findByLocalDate(LocalDate localDate) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int localDateDay = localDate.getDayOfMonth();

        return Arrays.stream(MenuDiscountType.values())
            .filter(menuDiscountType -> menuDiscountType.hasDayOfWeek(dayOfWeek))
            .filter(menuDiscountType -> menuDiscountType.hasDayOfMonth(localDateDay))
            .findAny()
            .orElse(MenuDiscountType.NONE);
    }

    public boolean hasDayOfWeek(DayOfWeek dayOfWeek) {
        return this.dayOfWeeks.contains(dayOfWeek);
    }

    public boolean hasDayOfMonth(int localDateDay) {
        return this.days.contains(localDateDay);
    }
}