package org.example;

 public record Name(String value) implements ValueObject<String> {
    public static int maxLength = 50;

    public Name {
        Name.validate(value);
    }

    public static void validate(String value) {
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't by empty");
        }
        if (value.split(" ").length < 2) {
            throw new IllegalArgumentException("Name must have two words or more");
        }
        if (value.length() > Name.maxLength) {
            throw new IllegalArgumentException("Name must be less than " + Name.maxLength + " length");
        }
    }

    public static Result<Name> create(String value) {
        try {
            var name = new Name(value);
            return Result.success(name);
        } catch (Exception exception) {
            return Result.failure(exception);
        }
    }

    @Override
    public boolean equals(ValueObjectDto<String> other) {
        if (other == null) {
            return false;
        }
        return this.value == other.value;
    }

}
