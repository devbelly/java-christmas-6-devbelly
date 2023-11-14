package christmas.domain;

import christmas.view.OutputView;

public class EventPlanner {
    public static void printPreview(Order order, Benefits benefits) {
        OutputView.printOrderMenu(order);
        OutputView.printTotalAmountsBeforeDiscount(order);

        OutputView.printPresentMenu(benefits.getPresent());
        OutputView.printBenefits(benefits.getBenefitDtos());

        Money totalBenefitsAmounts = benefits.getTotalBenefitsAmounts();

        OutputView.printTotalBenefitsAmounts(totalBenefitsAmounts);

        OutputView.printAmountToPay(order.getAmountToPay(benefits.getDiscountsAmounts()));
        OutputView.printEventBadge(benefits.getEventBadge());
    }
}
