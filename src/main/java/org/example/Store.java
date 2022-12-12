package org.example;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Product> products = new ArrayList<Product>();
    private double totalValue;
    private int totalAmount;
    private int nextAvailableId = 1;

    public List<Product> getProducts() {
        return this.products;
    }

    public double getTotalValue() {
        return this.totalValue;
    }

    public int getTotalAmount() {
        return this.totalAmount;
    }

    public int registerProduct(Product product) {
        product.setId(nextAvailableId++);
        product.active();
        this.products.add(product);
        this.totalAmount += product.getAmount();
        this.totalValue += product.getPrice() * product.getAmount();
        return product.getId();
    }

    public Result<Product> findProductById(int productId) {
        var productSearch = this.products.stream().filter(product -> product.getId() == productId).findFirst();
        if (productSearch.isEmpty()) {
            return Result.failure(new IndexOutOfBoundsException("Product with id " + productId + " not found"));
        }
        return Result.success(productSearch.get());
    }

    public Result<Product> removeProduct(int productId) {
        var foundProduct = this.findProductById(productId);
        if (foundProduct.isFailure) {
            return foundProduct;
        }
        var productIndex = this.products.indexOf(foundProduct.value);
        if (productIndex == -1) {
            return Result.failure(new Exception("Can't update product in list by ID" + productId));
        }
        this.products.remove(productIndex);
        return Result.success(foundProduct.value);
    }

    public Result<Product> changeProductPrice(Product product, double price) {
        var changePriceResult = product.setPrice(price);
        if (changePriceResult.isFailure) {
            return changePriceResult;
        }
        return Result.success(changePriceResult.value);
    }

    public Result<Product> changeProductAmount(Product product, int amount) {
        var changeAmountResult = product.adjustAmountAvailable(amount);
        if (changeAmountResult.isFailure) {
            return changeAmountResult;
        }
        return Result.success(changeAmountResult.value);
    }

    public Result<Product> updateProduct(Product product) {
        if (product.getId() == 0) {
            return Result.failure(new Exception("Can't update product without ID"));
        }
        this.products.stream().map(existingProduct ->
                existingProduct.getId() == product.getId() ? product : existingProduct
        );
        return Result.success(product);
    }

}
