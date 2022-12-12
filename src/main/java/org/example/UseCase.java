package org.example;

public interface UseCase<Output> {
    Result<Output> execute();
}
