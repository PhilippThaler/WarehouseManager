package com.philippthaler.app.model;

import com.philippthaler.app.exceptions.PositionFullException;
import com.philippthaler.app.utils.helpers.Database2DConfig;

public class Position {
  private Article article;
  private int numOfArticles;
  private Database2DConfig arrayPosition;

  private static final int MAX_AMOUNT_OF_ARTICLES = 10;

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
    this.article = article;
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
    if (numOfArticles > this.numOfArticles) {
      addNumOfArticles(numOfArticles - this.numOfArticles);
    } else {
      subtractNumOfArticles(this.numOfArticles - numOfArticles);
    }
  }

  public void addNumOfArticles(int amount) {
    checkAmount(amount);
    numOfArticles += amount;
  }

  public void subtractNumOfArticles(int amount) {
    if ((numOfArticles - amount) < 0) {
      numOfArticles = 0;
    }
    numOfArticles -= amount;
  }

  private void checkAmount(int amount) {
    if (numOfArticles + amount > MAX_AMOUNT_OF_ARTICLES) {
      throw new PositionFullException("Too many Articles in this position: " + numOfArticles + "+" + amount + "=" + (numOfArticles + amount) + "\nOnly " + MAX_AMOUNT_OF_ARTICLES + " allowed!");
    }
  }

  public static int getMaxAmountOfArticles() {
    return MAX_AMOUNT_OF_ARTICLES;
  }

  public boolean isEmpty() {
    return article == null;
  }

  public boolean isFull() {
    return numOfArticles == MAX_AMOUNT_OF_ARTICLES;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Position && ((Position) obj).article.equals(this.article) && ((Position) obj).numOfArticles == numOfArticles;

  }

  @Override
  public String toString() {
    return article + ", Amount: " + numOfArticles + ", " + arrayPosition;
  }
}

