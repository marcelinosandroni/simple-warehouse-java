package org.example;

public class Product {
    private int id;
    private Name name;
    private double price;
    private int amount;
    private boolean active;

    private Product(Name name, double price, PositiveNumber amount) {
        this.name = name;
        this.price = price;
        this.amount = amount.value();
        this.active = false;
    }

    public static Result<Product> create(Name name, double price, PositiveNumber amount) {
        var product = new Product(name, price, amount);
        return Result.success(product);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) throws Exception {
        if (this.name.equals(name)) {
            throw new Exception("Name must be different from current");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public Result<Product> setPrice(double price) {
        if (price < 0) {
            return Result.failure(new Exception("Price can't be negative"));
        }
        this.price = price;
        return Result.success(this);
    }

    public int getAmount() {
        return amount;
    }

    public Result<Product> adjustAmountAvailable(int amount) {
        if (amount < 0) {
            return Result.failure(new Exception("Amount can't be negative"));
        }
        // EMIT EVENT WHEN AMOUNT BELLOW 10
        this.amount = amount;
        return Result.success(this);
    }

    public void incrementQuantity(PositiveNumber increaseAmount) {
        this.amount += increaseAmount.value();
    }

    public void decrementQuantity(PositiveNumber decrementAmount) {
        this.amount -= decrementAmount.value();
    }


    public boolean active() {
        return active = true;
    }

    public void deactive() {
        this.active = false;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Product)) {
            return false;
        }
        return this.getId() == ((Product) other).getId();
    }
}
