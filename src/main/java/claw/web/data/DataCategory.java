package claw.web.data;

import org.jsoup.nodes.Document;

import java.util.LinkedList;
import java.util.List;
/*
get entry and short html on category
 */
public class DataCategory {
    private Document document;

    public DataCategory(Document document) {
        this.document = document;
    }

    /*
    @cssSelector: parse html to take list element that have url and some information of each article
    @cssSelectorSmaller: select smaller element to extract url. If you have no rule, set null or ""
    @include: str rule to choice url. If you have no rule, set null or ""
    @exclude: str rule to reject url. If you have no rule, set null or ""
     */
    public List<Article> getEntriesOnCategory(String cssSelector, String cssSelectorSmaller, String include, String exclude) {
        List<Article> dataArticleList = new LinkedList<>();

        document.select(cssSelector).forEach(x -> {
            String url = null;

            if (cssSelectorSmaller == null || cssSelectorSmaller.equals("")) {
                url = x.select("a").attr("abs:href");
            } else {
                url = x.select(cssSelectorSmaller).select("a").attr("abs:href");
            }

            if (url == null
                    || (include != null && !include.equals("") && !url.contains(include))
                    || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

            } else {
                Article data = new Article(url);
                data.setElement(x);
                dataArticleList.add(data);
            }
        });

        return dataArticleList;
    }

}
