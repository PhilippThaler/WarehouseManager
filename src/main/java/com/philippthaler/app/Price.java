package com.philippthaler;

import java.math.BigDecimal;

public class Price {

    private BigDecimal price;

    public Price() {
        price = new BigDecimal(0);
    }

    public Price(double price) {
        this.price = new BigDecimal(price);
    }

    public Price(long price) {
        this.price = new BigDecimal(price);
    }

    public void setPrice(long price) {
        this.price = BigDecimal.valueOf(price);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getPriceAsDouble() {
        return price.doubleValue();
    }

    public void add(double amount) {
        price = price.add(BigDecimal.valueOf(amount));
    }

    public void add(long amount) {
        price = price.add(BigDecimal.valueOf(amount));
    }

    public void add(BigDecimal amount) {
        price = price.add(amount);
    }

    public void subtract(double amount) {
        price = price.subtract(BigDecimal.valueOf(amount));
    }

    public void subtract(long amount) {
        price = price.subtract(BigDecimal.valueOf(amount));
    }

    public void subtract(BigDecimal amount) {
        price = price.subtract(amount);
    }

    @Override
    public String toString() {
        return price.toPlainString();
    }
}
