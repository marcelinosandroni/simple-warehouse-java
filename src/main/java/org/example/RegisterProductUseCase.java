package org.example;

import java.util.Scanner;

public class RegisterProductUseCase implements UseCase<Product> {
    Store store;

    RegisterProductUseCase(Store store) {
        this.store = store;
    }
    @Override
    public Result<Product> execute() {
        var createProductResult = this.createProduct();
        if (createProductResult.isFailure) {
            return createProductResult;
        }
        System.out.printf("%nCreated product %s with price R$ %.2f and quantity %d%n%n",
                createProductResult.value.getName().value(),
                createProductResult.value.getPrice(),
                createProductResult.value.getAmount()
        );
        if (createProductResult.isFailure) {
            return Result.failure(createProductResult.error);
        }
        var storedProductId = store.registerProduct(createProductResult.value);
        System.out.println("Product successfully registered in warehouse with ID " + storedProductId);
        createProductResult.value.setId(storedProductId);
        return Result.success(createProductResult.value);
    }

    private Result<Product> createProduct() {
        System.out.println("Create new product");
        var scanner = new Scanner(System.in);
        System.out.print("Name: ");
        var nameInput = scanner.nextLine();
        System.out.print("Price: ");
        var price = scanner.nextDouble();
        System.out.print("Quantity: ");
        var amountInput = scanner.nextInt();
        var nameResult = Name.create(nameInput);
        if (nameResult.isFailure) {
            return Result.failure(nameResult.error);
        }
        var amount = new PositiveNumber(amountInput);
        var productResult = Product.create(nameResult.value, price, amount);
        if (productResult.isFailure) {
            return Result.failure(productResult.error);
        }
        return Result.success(productResult.value);
    }
}
