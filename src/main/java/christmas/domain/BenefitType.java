package christmas.domain;

import java.time.LocalDate;

public enum BenefitType {
    CHRISTMAS_D_DAY(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25)),
        "크리스마스 디데이 할인"),
    WEEKDAYS(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)), "평일 할인"),
    WEEKENDS(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)), "주말 할인"),
    SPECIAL(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)), "특별 할인"),
    PRESENT(EventPeriod.between(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)), "증정 이벤트"),
    NONE(EventPeriod.between(LocalDate.of(0, 0, 0), LocalDate.of(0, 0, 0)), "없음");

    private EventPeriod eventPeriod;
    private String title;

    BenefitType(EventPeriod eventPeriod, String title) {
        this.eventPeriod = eventPeriod;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
