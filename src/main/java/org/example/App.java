package org.example;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        String[] options = {
                "Register new product",
                "List store",
                "Adjust Price",
                "Adjust Quantity",
                "Remove product"
        };
        var store = new Store();
        var scanner = new Scanner(System.in);
        UseCase[] actions = {
            new RegisterProductUseCase(store),
            new ListWarehouseUseCase(store),
            new AdjustProductPriceUseCase(store),
            new AdjustProductQuantityUseCase(store, scanner),
            new RemoveProductFromWarehouseUseCase(store, scanner),
        };
        var menu = new Menu("Store", options);
        int selectedOption;
        do {
            selectedOption = menu.render();
            if (selectedOption == 0) {
                System.out.println("Goodbye...");
                System.exit(0);
            }
            if (selectedOption > options.length) {
                System.out.println("Invalid option, try again");
                continue;
            }
            var useCaseResult = actions[selectedOption - 1].execute();
            if (useCaseResult.isFailure) {
                System.out.printf("%nError: %s%n", useCaseResult.error.getMessage());
                System.out.println("A problem occurred executing the last operation, try again.");
            }
        } while (selectedOption != 0);
    }
}
