package org.example;

import java.util.Scanner;

public class AdjustProductPriceUseCase implements UseCase<Product> {
    Store store;

    AdjustProductPriceUseCase(Store store) {
        this.store = store;
    }

    @Override
    public Result<Product> execute() {
        var scanner = new Scanner(System.in);
        System.out.print("Product id: ");
        var productIdInput = scanner.nextInt();
        var searchResult = store.findProductById(productIdInput);
        if (searchResult.isFailure) {
            var message = "Product with ID " + productIdInput + " not found";
            System.out.println(message);
            return Result.failure(new Exception(message));
        }
        var product = searchResult.value;
        System.out.printf("Product %s has current price of %.2f%n", product.getName().value(), product.getPrice());
        System.out.print("Input new Price: ");
        var priceInput = scanner.nextDouble();
        return this.store.changeProductPrice(searchResult.value, priceInput);
    }
}
