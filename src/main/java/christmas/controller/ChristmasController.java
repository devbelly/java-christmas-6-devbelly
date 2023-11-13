package christmas.controller;

import christmas.dtos.OrderLineDto;
import christmas.view.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {

    public static List<OrderLineDto> getOrderLineDtos() {
        try {
            OutputView.printOrderMenuMessage();
            List<OrderLineDto> dtos = InputView.readMenus();
            validateDuplicateMenus(dtos);
            return dtos;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return getOrderLineDtos();
        }
    }


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

    private static void validateDuplicateMenus(List<OrderLineDto> dtos) {
        int originalSize = dtos.size();
        int distinctSize = (int) dtos.stream()
            .map(OrderLineDto::getMenuDetail)
            .distinct()
            .count();

        if (originalSize != distinctSize) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_MENU_INPUT.getMessage());
        }
    }
}
