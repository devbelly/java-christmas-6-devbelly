package christmas.domain;

import java.util.Objects;

public class Money {


    public static final Money ZERO = new Money(0);

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    public Money plus(Money input) {
        return new Money(value + input.value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}