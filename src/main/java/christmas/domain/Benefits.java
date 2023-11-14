package christmas.domain;

import christmas.dtos.BenefitDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Benefits {

    private final Map<BenefitType, Integer> benefits = new HashMap<>();

    public Benefits() {
        for (BenefitType benefitType : BenefitType.values()) {
            benefits.put(benefitType, 0);
        }
    }

    public void updateMenuDiscounts(MenuDiscounts menuDiscounts) {
        benefits.put(BenefitType.WEEKDAYS, menuDiscounts.sumOfWeekDaysDiscounts().getValue());
        benefits.put(BenefitType.WEEKENDS, menuDiscounts.sumOfWeekendsDiscounts().getValue());
    }

    public void updateTotalDiscounts(TotalDiscounts totalDiscounts) {
        benefits.put(BenefitType.SPECIAL, totalDiscounts.sumOfSpecialDiscounts().getValue());
        benefits.put(BenefitType.CHRISTMAS_D_DAY,
            totalDiscounts.sumOfChristmasDiscounts().getValue());
    }

    public void updatePresent(Order order) {
        Money money = order.getTotalAmounts();
        PresentItem presentItem = PresentItem.findByTotalPrice(money.getValue());
        benefits.put(BenefitType.PRESENT, presentItem.getPrice());
    }

    public PresentItem getPresent() {
        int money = benefits.get(BenefitType.PRESENT);
        return PresentItem.findByPresentPrice(money);
    }

    public List<BenefitDto> getBenefitDtos() {
        return benefits.keySet()
            .stream()
            .filter(benefitType -> benefits.get(benefitType) > 0)
            .map(benefitType -> BenefitDto.toDto(benefitType, benefits.get(benefitType)))
            .toList();
    }

    public Money getTotalBenefitsAmounts() {
        return benefits.keySet()
            .stream()
            .map(benefitType -> new Money(benefits.get(benefitType)))
            .reduce(new Money(0), Money::plus);
    }

    public EventBadge getEventBadge() {
        return EventBadge.findByMoney(getTotalBenefitsAmounts().getValue());
    }
}