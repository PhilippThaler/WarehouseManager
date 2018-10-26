package com.philippthaler;

import java.util.InputMismatchException;

public class Position {

    private Article article;
    private int numOfArticles;

    public Position() {
        article = null;
        numOfArticles = 0;
    }

    public Position(Article article) {
        this.article = article;
        numOfArticles = 0;
    }

    public Position(Article article, int numOfArticles) {
        if (numOfArticles < 0) {
            throw new InputMismatchException("The number can't be negative!");
        }
        this.article = article;
        this.numOfArticles = numOfArticles;
    }

    public Article getArticle() {
        return article;
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
        if((numOfArticles-amount) < 0) {
            numOfArticles = 0;
        }
        numOfArticles -= amount;
    }

    @Override
    public String toString() {
        return "Article: " + article.getName() + ": " + numOfArticles;
    }
}

