package christmas.controller;

import christmas.view.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    public static int getExpectedVisitDate() {
        try {
            OutputView.printExpectedVisitDateMessage();
            int day = InputView.readInteger();
            validateExpectedVisitDate(day);
            return day;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return getExpectedVisitDate();
        }
    }

    private static void validateExpectedVisitDate(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_INPUT.getMessage());
        }
    }
}
