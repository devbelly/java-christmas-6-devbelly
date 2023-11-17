package christmas.domain;

import java.time.LocalDate;

public class EventPeriod {

    private static final String INVALID_PERIOD_ERROR_MESSAGE = "[ERROR] 시작 일시는 종료 일 이후일 수 없습니다.";

    private LocalDate startDate;
    private LocalDate endDate;

    public EventPeriod(LocalDate startDate, LocalDate endDate) {
        validatePeriod(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static EventPeriod between(LocalDate startDate, LocalDate endDate) {
        return new EventPeriod(startDate, endDate);
    }

    private void validatePeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(INVALID_PERIOD_ERROR_MESSAGE);
        }
    }
}
