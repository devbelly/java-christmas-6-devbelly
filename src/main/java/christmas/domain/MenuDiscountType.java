package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public enum MenuDiscountType {

    WEEKDAYS(List.of(
        DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY)),
    WEEKENDS(List.of(
        DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)),
    NONE(List.of());

    List<DayOfWeek> dayOfWeeks;

    MenuDiscountType(List<DayOfWeek> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    public static MenuDiscountType findByLocalDate(LocalDate localDate) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int localDateDay = localDate.getDayOfMonth();

        return Arrays.stream(MenuDiscountType.values())
            .filter(menuDiscountType -> menuDiscountType.hasDayOfWeek(dayOfWeek))
            .findAny()
            .orElse(MenuDiscountType.NONE);
    }

    public boolean hasDayOfWeek(DayOfWeek dayOfWeek) {
        return this.dayOfWeeks.contains(dayOfWeek);
    }
}