package com.philippthaler.app.model;

import com.philippthaler.app.utils.helpers.Price;

/**
 * Interface that describes an Article.
 */
public interface Article {
  String getName();
  String getItemNumber();
  Price getPrice();

  void setName(String name);
  void setItemNumber(String itemNumber);
  void setPrice(Price price);
  void setSupplier(ArticleInfo supplier);
  void setPackagingUnit(ArticleInfo packagingUnit);

  boolean equals(Object obj);
  String toString();

}
