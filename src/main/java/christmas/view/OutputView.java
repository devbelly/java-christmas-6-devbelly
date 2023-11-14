package christmas.view;

import christmas.domain.EventBadge;
import christmas.domain.Money;
import christmas.domain.Order;
import christmas.domain.OrderLine;
import christmas.domain.PresentItem;
import christmas.dtos.BenefitDto;
import java.time.LocalDate;
import java.util.List;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EXPECTED_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private static final String BENEFIT_PREVIEW_MESSAGE = "%d월 %d일 에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    private static final String ORDER_MENU = "<주문 내역>";

    private static final String TOTAL_AMOUNTS_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";

    private static final String WON = "%,d원";

    private static final String PRESENT_MENU = "<증정 메뉴>";

    private static final String BENEFIT_MENU = "<혜택 내역>";

    private static final String BENEFIT_DETAIL = "%s: -%,d원";

    private static final String TOTAL_BENEFITS_AMOUNTS = "<총혜택 금액>";

    private static final String TOTAL_AMOUNTS_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";

    private static final String DECEMBER_EVENT_BADGE = "<12월 이벤트 뱃지>";

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

    public static void printPresentMenu(PresentItem presentItem) {
        System.out.println(PRESENT_MENU);
        String presentItemTitle = presentItem.getTitle();
        if (presentItem != PresentItem.NONE) {
            presentItemTitle.concat(" 1개");
        }
        System.out.println(presentItemTitle);
        printEmptyLine();
    }

    public static void printBenefits(List<BenefitDto> dtos) {
        System.out.println(BENEFIT_MENU);
        if (dtos.isEmpty()) {
            System.out.println("없음");
            printEmptyLine();
            return;
        }
        dtos.forEach(OutputView::printBenefit);
        printEmptyLine();
    }

    public static void printTotalBenefitsAmounts(Money money) {
        System.out.println(TOTAL_BENEFITS_AMOUNTS);
        if (!money.equals(Money.ZERO)) {
            System.out.print("-");
        }
        printMoney(money);
        printEmptyLine();
    }

    public static void printAmountToPay(Money money) {
        System.out.println(TOTAL_AMOUNTS_AFTER_DISCOUNT);
        printMoney(money);
        printEmptyLine();
    }

    public static void printEventBadge(EventBadge badge) {
        System.out.println(DECEMBER_EVENT_BADGE);
        System.out.println(badge.getTitle());
        printEmptyLine();
    }

    private static void printMoney(Money money) {
        System.out.println(WON.formatted(money.getValue()));
    }

    private static void printBenefit(BenefitDto dto) {
        String title = dto.getBenefitType().getTitle();
        System.out.println(BENEFIT_DETAIL.formatted(title, dto.getDiscountAmount()));
    }
}
