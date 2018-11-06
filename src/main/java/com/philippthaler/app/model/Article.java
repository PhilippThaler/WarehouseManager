package com.philippthaler.app.model;

import com.philippthaler.app.utils.helpers.Price;

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
