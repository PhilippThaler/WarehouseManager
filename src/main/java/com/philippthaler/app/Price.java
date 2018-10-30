package com.philippthaler.app;

import java.math.BigDecimal;

public class Price implements Comparable {

  private BigDecimal price;
  private String symbol;

  public Price(BigDecimal price, String symbol) {
    this.price = price;
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
    return price.toPlainString();
  }

  @Override
  public int compareTo(Object o) {
    if (o instanceof Price) {
      Price p = (Price) o;
      return (price.compareTo(p.price));
    } else {
      return -1;
    }
  }
}
