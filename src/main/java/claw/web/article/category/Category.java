package claw.web.article.category;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Category {
    
    String categoryUrl;
    Document document;
    String cssSelector;

    public Elements getCategoryUrl() {
        return document.select(cssSelector);
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public void setCssSelector(String cssSelector){
        this.cssSelector = cssSelector;
    }

}
