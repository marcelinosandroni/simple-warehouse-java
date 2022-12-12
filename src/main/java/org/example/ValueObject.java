package org.example;

class ValueObjectDto<Type> {
    Type value;
}
public interface ValueObject<Type> {
    boolean equals(ValueObjectDto<Type> other);
}
