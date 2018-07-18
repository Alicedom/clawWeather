package claw.web.data;

import claw.web.html.Html;
import org.jsoup.nodes.Document;

import java.util.List;


public class MainTestGetDataCategory {

    public static void main(String[] args) {
        Document document = Html.getDocument("http://triethoc.edu.vn");
        DataCategory category = new DataCategory(document);

//        List<DataArticle> dataArticles = category.getEntriesOnCategory("#ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div",
//        "","","");

        List<Article> dataArticles = category.getEntriesOnCategory("#ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div > div > ul > li",
                "","","");
        dataArticles.forEach(System.out::println);
        System.out.println("dataArticles.size() = " + dataArticles.size());
    }
}
