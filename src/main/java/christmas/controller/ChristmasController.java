package christmas.controller;

import christmas.domain.BenefitCalculationFactory;
import christmas.domain.Benefits;
import christmas.domain.EventPlanner;
import christmas.domain.Order;
import christmas.dtos.OrderLineDto;
import christmas.util.OrderLineMapper;
import christmas.view.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;

public class ChristmasController {

    public static void run() {
        OutputView.printWelcomeMessage();
        int day = getExpectedVisitDate();

        Order order = getOrder();

        LocalDate orderDate = LocalDate.of(2023, 12, day);

        Benefits benefits = BenefitCalculationFactory.calculateBenefits(order, orderDate);

        OutputView.printBenefitPreviewMessage(orderDate);

        EventPlanner.printPreview(order, benefits);
    }

    public static Order getOrder() {
        try {
            OutputView.printOrderMenuMessage();
            List<OrderLineDto> dtos = InputView.readMenus();
            validateDuplicateMenus(dtos);

            Order order = new Order(OrderLineMapper.orderLineDtosToOrderLines(dtos));

            return order;
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return getOrder();
        }
    }


    public static int getExpectedVisitDate() {
        try {
            OutputView.printExpectedVisitDateMessage();
            int day = InputView.readInteger();
            validateExpectedVisitDate(day);
            return day;
        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
            System.out.println(e.getClass());
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
