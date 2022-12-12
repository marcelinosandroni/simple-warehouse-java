package org.example;

import java.util.Scanner;

public class AdjustProductQuantityUseCase extends FindProductByIdUseCase {
    AdjustProductQuantityUseCase(Store store, Scanner scanner) {
        super(store, scanner);
    }

    @Override
    public Result<Product> execute() {
        var findProductResult = this.renderFindProductById();
        if (findProductResult.isFailure) {
            return findProductResult;
        }
        var product = findProductResult.value;
        System.out.printf("Product %s has current quantity of %d%n", product.getName().value(), product.getAmount());
        System.out.print("Input new Quantity: ");
        var quantityInput = scanner.nextInt();
        return this.store.changeProductPrice(product, quantityInput);
    }
}
