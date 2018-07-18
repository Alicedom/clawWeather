package claw.web.data;

import claw.web.html.Html;
import org.jsoup.nodes.Document;

import java.util.List;

public class MainTestGetDataArticleGetSrcFromElement {

    public static void main(String[] args) {
        Document document = Html.getDocument("http://triethoc.edu.vn");
        DataCategory category = new DataCategory(document);

        List<Article> dataArticles = category.getEntriesOnCategory("#ctl00_Default1__Body > div > div.page-wrapper.home-wrapper",
                "","","");

        dataArticles.forEach(x->{
            DataArticle article = new DataArticle(x);
            article.getSourceFromElement();
        });

        dataArticles.forEach(x-> System.out.println("x.getSrcList().toString() = " + x.getSrcList().toString()));
        dataArticles.forEach(x-> System.out.println("x.getSrcList().size() = " + x.getSrcList().size()));
        System.out.println("dataArticles = " + dataArticles.size());
    }
}
