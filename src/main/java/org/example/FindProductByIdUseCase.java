package org.example;

import java.util.Scanner;

public abstract class FindProductByIdUseCase implements UseCase<Product>{
    Store store;
    Scanner scanner;

    FindProductByIdUseCase(Store store, Scanner scanner) {
        this.store = store;
        this.scanner = scanner;
    }


    public Result<Product> renderFindProductById() {
        System.out.print("Product id: ");
        var productIdInput = scanner.nextInt();
        var searchResult = store.findProductById(productIdInput);
        if (searchResult.isFailure) {
            var message = "Product with ID " + productIdInput + " not found";
            System.out.println(message);
            return Result.failure(new Exception(message));
        }
        return Result.success(searchResult.value);
    }
}
