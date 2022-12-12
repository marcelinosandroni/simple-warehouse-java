package org.example;

public class ListWarehouseUseCase implements UseCase<Integer> {
    Store store;

    ListWarehouseUseCase(Store store) {
        this.store = store;
    }

    @Override
    public Result<Integer> execute() {
        var products = store.getProducts();
        if (products.isEmpty()) {
            var message = "No registered products yet";
            System.out.println(message);
            return Result.failure(new Exception(message));
        }
        String printLineFormat = "%-10s %-50s %-10s %-10s%n";
        System.out.printf(printLineFormat, "Id", "Name", "Price", "Amount");
        for (Product product : store.getProducts()) {
            System.out.printf("%-10d %-50s %-10.2f %-10d%n",
                    product.getId(),
                    product.getName().value(),
                    product.getPrice(),
                    product.getAmount()
            );
        }
        System.out.printf("%nTotal Amount: %-20d Total Price: %.2f%n", store.getTotalAmount(), store.getTotalValue());
        return Result.success(store.getTotalAmount());
    }
}
