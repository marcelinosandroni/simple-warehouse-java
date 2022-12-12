package org.example;

public record PositiveNumber(int value) implements ValueObject<Integer> {
    public PositiveNumber {
        PositiveNumber.validate(value);
    }

    public static void validate(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can't be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be positive number");
        }
    }
    @Override
    public boolean equals(ValueObjectDto<Integer> other) {
        if (other.value == null) {
            return false;
        }
        return this.value == other.value;
    }
}
