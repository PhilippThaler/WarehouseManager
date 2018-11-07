package com.philippthaler.app.model;

import com.philippthaler.app.utils.helpers.Price;

import java.util.HashMap;

/**
 * Implementation of the Article interface
 */
public class ArticleNormal implements Article {

  private String name;
  private String itemNumber;
  private Price price;
  private HashMap<ArticleInfo, String> infos;


  public ArticleNormal(String name, Price price, ArticleInfo... infos) {
    this.name = name;
    this.itemNumber = generateItemNumber(name);
    this.price = price;
    this.infos = getInfoMapFromArray(infos);
  }

  public String getName() {
    return name;
  }

  public String getItemNumber() {
    return itemNumber;
  }

  public Price getPrice() {
    return price;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setItemNumber(String itemNumber) {
    this.itemNumber = itemNumber;
  }

  public void setPrice(Price price) {
    this.price = price;
  }

  /**
   * Getter Method that takes an Array of ArticleInfo and returns a Hashmap.
   * @param infos The array
   * @return HashMap
   */
  public HashMap<ArticleInfo, String> getInfoMapFromArray(ArticleInfo... infos) {
    HashMap<ArticleInfo, String> infoMap = new HashMap<>();
    for (ArticleInfo info : infos) {
      infoMap.put(info, info.getName());
    }
    return infoMap;
  }

  private String generateItemNumber(String articleName) {
    return Math.abs(articleName.hashCode()) + "";
  }


  /**
   * Implementation of the equals method
   * @param obj An object
   * @return Returns true, if every field of an ArticleInfo is equals to every field of another one.
   */
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Article && ((ArticleNormal) obj).name.equals(name) && ((ArticleNormal) obj).itemNumber.equals(itemNumber) && ((ArticleNormal) obj).price.equals(price) && ((ArticleNormal) obj).price.equals(price);
  }

  @Override
  public String toString() {
    return "Article: " + name + ", Itemnumber: " + itemNumber + ", Price: " + price.getPrice();
  }
}
