package com.philippthaler.app.utils.helpers;

import java.math.BigDecimal;

/**
 * Class that describes a price object. Additionally saves the currency symbol.
 * Implements the Comparable interface to compare prices.
 */
public class Price implements Comparable<Price> {

  private BigDecimal price;
  private String symbol;

  public Price(double price) {
    this(price, "€");
  }

  public Price(BigDecimal price) {
    this(price, "€");
  }

  public Price(String price) {
    this(price, "€");
  }

  public Price(double price, String symbol) {
    setPrice(price);
    this.symbol = symbol;
  }

  public Price(BigDecimal price, String symbol) {
    setPrice(price);
    this.symbol = symbol;
  }

  public Price(String price, String symbol) {
    this(new BigDecimal(price), symbol);
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

  public void add(double amount) {
    price = price.add(BigDecimal.valueOf(amount));
  }

  public void add(BigDecimal amount) {
    price = price.add(amount);
  }


  public void subtract(double amount) {
    price = price.subtract(BigDecimal.valueOf(amount));
  }

  public void subtract(BigDecimal amount) {
    price = price.subtract(amount);
  }

  @Override
  public String toString() {
    return symbol + " " + price.toPlainString();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Price && ((Price) obj).compareTo(this) == 0;
  }

  @Override
  public int compareTo(Price price) {
    return this.price.compareTo(price.price);
  }
}
