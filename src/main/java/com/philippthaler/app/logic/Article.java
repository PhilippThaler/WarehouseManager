package com.philippthaler.app.logic;

import com.philippthaler.app.utils.Price;

import java.util.HashMap;

public class Article {

  private String name;
  private String itemNumber;
  private Price price;
  private HashMap<ArticleInfo, String> infos;


  public Article(String name, String itemNumber, Price price, ArticleInfo... infos) {
    this.name = name;
    this.itemNumber = itemNumber;
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

  private HashMap<ArticleInfo, String> getInfoMapFromArray(ArticleInfo... infos) {
    HashMap<ArticleInfo, String> infoMap = new HashMap<>();
    for (ArticleInfo info : infos) {
      infoMap.put(info, info.getName());
    }
    return infoMap;
  }


  @Override
  public boolean equals(Object obj) {
    return obj instanceof Article && ((Article) obj).name==name && ((Article) obj).itemNumber.equals(itemNumber)&&((Article) obj).price.equals(price) && ((Article) obj).price.equals(price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Article: " + name + ", Itemnumber: " + itemNumber + ", Price: " + price.getPrice());
    return sb.toString();
  }
}
