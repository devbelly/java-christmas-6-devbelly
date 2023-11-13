package christmas.view;

import christmas.domain.Money;
import christmas.domain.Order;
import christmas.domain.OrderLine;
import java.time.LocalDate;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EXPECTED_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private static final String BENEFIT_PREVIEW_MESSAGE = "%d월 %d일 에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    private static final String ORDER_MENU = "<주문 내역>";

    private static final String TOTAL_AMOUNTS_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    private static final String WON = "%.d원";

    private OutputView() {
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printExpectedVisitDateMessage() {
        System.out.println(EXPECTED_VISIT_DATE_MESSAGE);
    }

    public static void printOrderMenuMessage() {
        System.out.println(ORDER_MENU_MESSAGE);
    }

    public static void printBenefitPreviewMessage(LocalDate date) {
        System.out.println(
            BENEFIT_PREVIEW_MESSAGE.formatted(date.getMonthValue(), date.getDayOfMonth())
        );
    }

    public static void printOrderMenu(Order order) {
        System.out.println(ORDER_MENU);
        order.getOrderLines()
            .forEach(OutputView::printOrderLine);
        printEmptyLine();

    }

    public static void printTotalAmountsBeforeDiscount(Order order) {
        System.out.println(TOTAL_AMOUNTS_BEFORE_DISCOUNT);
        System.out.println(order.getTotalAmounts());
        printEmptyLine();
    }

    private static void printOrderLine(OrderLine orderLine) {
        System.out.println(
            orderLine.getMenuDetail().getTitle() + "  " + orderLine.getQuantity() + "개");
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    private static void printMoney(Money money) {
        System.out.println(WON.formatted(money.getValue()));
    }
}
