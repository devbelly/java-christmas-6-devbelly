package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dtos.OrderLineDto;
import christmas.util.StringUtils;
import java.util.List;

public class InputView {

    private InputView() {
    }

    public static int readInteger() {
        try {
            String input = Console.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_INPUT.getMessage());
        }
    }

    public static List<OrderLineDto> readMenus() {
        try {
            String input = Console.readLine();
            return StringUtils.splitStringToOrderLines(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
