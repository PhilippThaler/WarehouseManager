package com.philippthaler.app.logic;

import com.philippthaler.app.utils.Price;

import java.util.Map;

public interface Article {
  String getName();
  String getItemNumber();
  Price getPrice();

  void setName(String name);
  void setItemNumber(String itemNumber);
  void setPrice(Price price);

  boolean equals(Object obj);
  String toString();

}
