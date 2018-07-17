package claw.web.category;

import claw.web.article.Article;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/*
get list url of article
get sapo, title, thumbnail, date...  if it have
 */

public class CategoryParse {
    public Category getList(Element element, String cssSelector, String include, String exclude) {

        List<String> entryList = new ArrayList<>();
        List<Article> list = new ArrayList<>();

        Elements elements = element.select(cssSelector);

        elements.forEach(x -> {
            Article article = new Article();
        });


    }
}
