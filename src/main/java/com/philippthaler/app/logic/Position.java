package com.philippthaler.app.logic;

import com.philippthaler.app.utils.Database2DConfig;

public class Position {

  private Article article;
  private int numOfArticles;
  private Database2DConfig arrayPosition;

  public Position(int column, int row) {
    this(null, column, row);
  }

  public Position(Database2DConfig config) {
    this(null, config.getColumn(), config.getRow());
  }

  public Position(Article article, int column, int row) {
    this(article, new Database2DConfig(column, row));
  }

  public Position(Article article, Database2DConfig config) {
    article = article;
    numOfArticles = 0;
    arrayPosition = config;
  }

  public Article getArticle() {
    return article;
  }

  public Database2DConfig getArrayPosition() {
    return arrayPosition;
  }

  public int getNumOfArticles() {
    return numOfArticles;
  }

  public void setArticle(Article article) {
    this.article = article;
  }

  public void setNumOfArticles(int numOfArticles) {
    this.numOfArticles = numOfArticles;
  }

  public void addNumOfArticles(int amount) {
    numOfArticles += amount;
  }

  public void subtractNumOfArticles(int amount) {
    if ((numOfArticles - amount) < 0) {
      numOfArticles = 0;
    }
    numOfArticles -= amount;
  }

  public boolean isEmpty() {
    return article == null;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Position && ((Position) obj).article.equals(this.article) && ((Position) obj).numOfArticles == numOfArticles;

  }

  @Override
  public String toString() {
    return "Article: " + article + ", Number: " + numOfArticles + arrayPosition;
  }
}

