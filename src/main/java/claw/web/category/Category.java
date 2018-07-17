package claw.web.category;

import claw.web.article.Article;

import java.util.List;


public class Category {

    String categoryUrl;
    List<String> entryList;
    List<Article> articleList;

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public List<String> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<String> entryList) {
        this.entryList = entryList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

}
