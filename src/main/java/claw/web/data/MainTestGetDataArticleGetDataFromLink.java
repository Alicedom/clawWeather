package claw.web.data;

import claw.web.html.Html;
import org.jsoup.nodes.Document;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainTestGetDataArticleGetDataFromLink {

    public static void main(String[] args) {
        String url = "http://triethoc.edu.vn/vi/truong-phai-triet-hoc/chu-nghia-hien-sinh/ly-thuyet-ve-su-hien-dien-hinh-thuc-cua-cai-toi_842.html";
        Article dataArticle = new Article(url);
        DataArticle article = new DataArticle(dataArticle);
        Document document = Html.getDocument(url);

        Map<String, String> cssQueryText = new Hashtable<>();
        cssQueryText.put("title", "#ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div > div > ul > li > div > h4");
        cssQueryText.put("date", " #ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div > div > ul > li > div > span");
        cssQueryText.put("sapo", "#ctl00_Default1__Body > div > div.page-wrapper.home-wrapper > div > div > ul > li > div > p");
        article.getParseText(document, cssQueryText);

        List<String> cssContent= new LinkedList<>();
        cssContent.add("#ctl00_Default1__Body > div.page-wrapper.page-wrapper2 > div");
        article.getParseDataFromLink(document, "","script,.hidden,form, div.content-comments, div.content-more");

        System.out.println("article = " + article.getArticle().toString());
    }
}
