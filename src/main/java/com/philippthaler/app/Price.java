package com.philippthaler.app;

import java.math.BigDecimal;

public class Price implements Comparable<Price> {

  private BigDecimal price;
  private String symbol;

  public Price(long price, String symbol) {
    setPrice(price);
    this.symbol = symbol;
  }

  public Price(double price, String symbol) {
    setPrice(price);
    this.symbol = symbol;
  }

  public Price(BigDecimal price, String symbol) {
    setPrice(price);
    this.symbol = symbol;
  }

  public void setPrice(long price) {
    this.price = BigDecimal.valueOf(price);
  }

  public void setPrice(double price) {
    this.price = BigDecimal.valueOf(price);
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public double getDoublePrice() {
    return price.doubleValue();
  }

  public long getLongPrice() {
    return price.longValue();
  }

  public void add(long amount) {
    price = price.add(BigDecimal.valueOf(amount));
  }

  public void add(double amount) {
    price = price.add(BigDecimal.valueOf(amount));
  }

  public void add(BigDecimal amount) {
    price = price.add(amount);
  }

  public void subtract(long amount) {
    price = price.subtract(BigDecimal.valueOf(amount));
  }

  public void subtract(double amount) {
    price = price.subtract(BigDecimal.valueOf(amount));
  }

  public void subtract(BigDecimal amount) {
    price = price.subtract(amount);
  }

  @Override
  public String toString() {
    return symbol+ " " + price.toPlainString();
  }

  @Override
  public int compareTo(Price price) {
    return this.price.compareTo(price.price);
  }
}
