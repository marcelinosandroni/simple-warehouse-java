package org.example;

import java.util.Scanner;

public class RemoveProductFromWarehouseUseCase implements UseCase<Product> {
    Store store;
    Scanner scanner;

    public RemoveProductFromWarehouseUseCase(Store store, Scanner scanner) {
        this.store = store;
        this.scanner = scanner;
    }

    @Override
    public Result<Product> execute() {
        System.out.println("Product ID to remove: ");
        var productId = scanner.nextInt();
        return this.store.removeProduct(productId);
    }
}
