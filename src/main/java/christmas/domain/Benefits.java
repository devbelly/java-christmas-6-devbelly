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
}
