package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Benefits {

    private final Map<BenefitType, Integer> benefits = new HashMap<>();

    public Benefits() {
        for (BenefitType benefitType : BenefitType.values()) {
            benefits.put(benefitType, 0);
        }
    }

    public void updatePresent(Order order) {
        Money money = order.getTotalAmounts();
        PresentItem presentItem = PresentItem.findByTotalPrice(money.getValue());
        benefits.putIfAbsent(BenefitType.PRESENT, presentItem.getPrice());
    }
}