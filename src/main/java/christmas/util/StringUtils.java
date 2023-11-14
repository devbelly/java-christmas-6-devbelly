package christmas.util;

import christmas.domain.MenuDetail;
import christmas.dtos.OrderLineDto;
import christmas.view.ErrorMessage;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    private StringUtils() {

    }

    public static List<OrderLineDto> splitStringToOrderLines(String input) {
        try {
            return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(StringUtils::splitMenusToDto)
                .toList();

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_INPUT.getMessage());
        }
    }

    private static OrderLineDto splitMenusToDto(String input) {
        try {
            List<String> inputs = Arrays.stream(input.split("-"))
                .map(String::trim)
                .toList();

            validateInput(inputs);

            String menuDetail = inputs.get(0);
            String quantity = inputs.get(1);

            return new OrderLineDto(MenuDetail.findByTitle(menuDetail), Integer.parseInt(quantity));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_INPUT.getMessage());
        }
    }

    private static void validateInput(List<String> inputs) {
        if (inputs.size() != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_INPUT.getMessage());
        }
    }
}