package christmas.domain;

import java.time.LocalDate;

public enum BenefitType {
    CHRISTMAS_D_DAY(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25))),
    WEEKDAYS(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31))),
    WEEKENDS(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31))),
    SPECIAL(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31))),
    PRESENT(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31))),
    NONE(EventPeriod.between(LocalDate.of(0, 0, 0), LocalDate.of(0, 0, 0)));

    private EventPeriod eventPeriod;

    BenefitType(EventPeriod eventPeriod) {
        this.eventPeriod = eventPeriod;
    }
}
