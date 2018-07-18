package claw.web.article.category;

import claw.web.article.article.Article;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/*
get list url of article
get sapo, title, thumbnail, date...  if it have
 */

public class CategoryParse {
    private String urlcategory;
    private Document document;
    private String cssSelector;
    private String include;
    private String exclude;

    private String urlCssQuery;
    private String thumbnailURLCSSQuery;
    private String titleCSSQuery;
    private String dateCSSQuery;
    private String sapoCSSQuery;
    private String authorCSSQuery;
    private String bodyCSSQuery;
    private String cssRemoveContent;
    private String urlSource;
    private String urlTarget;

    public void getList() {
        List<String> entryList = new ArrayList<>();
        List<Article> list = new ArrayList<>();

        Elements elements = document.select(cssSelector);

        elements.forEach(x -> {
            Article article = new Article();
        });
        
    }
}
