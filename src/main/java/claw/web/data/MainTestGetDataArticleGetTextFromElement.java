package claw.web.data;

import claw.web.html.Html;
import org.jsoup.nodes.Document;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MainTestGetDataArticleGetTextFromElement {

    public static void main(String[] args) {
        Document document = Html.getDocument("http://triethoc.edu.vn");
        DataCategory category = new DataCategory(document);

        List<Article> dataArticles = category.getEntriesOnCategory("#ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div:nth-child(5) > div > ul > li",
                "","","");

        Map<String, String> cssQueryText = new Hashtable<>();
        /*
        can get
         */
        cssQueryText.put("title","h4");
        cssQueryText.put("date"," span");
        cssQueryText.put("sapo", "p");

        /*
        cant to get
         */
//        cssQueryText.put("title", "#ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div > div > ul > li > div > h4");
//        cssQueryText.put("date", " #ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div > div > ul > li > div > span");
//        cssQueryText.put("sapo", "#ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div > div > ul > li > div > p");
        dataArticles.forEach(x->{
            DataArticle article = new DataArticle(x);
            article.getParseText(x.getElement(),cssQueryText);
        });

        dataArticles.forEach(System.out::println);
        System.out.println("dataArticles = " + dataArticles.size());
    }
}
