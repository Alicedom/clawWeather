package claw.web.data;

import claw.web.html.Html;
import org.jsoup.nodes.Document;

import java.util.LinkedList;
import java.util.List;

public class MainTestDataArticleGetBody {

    public static void main(String[] args) {
        String homelink = "http://kenhthoitiet.am.local/box/featured/lo-lang-khong-khi-lanh-tran-xuong-nam-bo-chuyen-gia-thoi-tiet-tran-an-101789/";




        String css = "#post-101789 > div.td-post-content > header > p > b," +
                "#post-101789 > div.td-post-header > header > h1," +
                "#post-101789 > div.td-post-header > header > div > span > time," +
                "#post-101789 > footer > div.td-post-source-tags > div > div > a";

        Article article = new Article(homelink);
        Document html = Html.getDocument(homelink);

        List<String> css2 = new LinkedList<>();
        css2.add("#post-101789 > div.td-post-content > header > p > b");
        css2.add("#post-101789 > div.td-post-header > header > h1");
        css2.add("#post-101789 > div.td-post-header > header > div > span > time");
        css2.add("#post-101789 > footer > div.td-post-source-tags > div > div > a");
        System.out.println("html = " + html.select(css));
        DataArticle dataArticle = new DataArticle(homelink);
        dataArticle.getParseDataFromLink(html, css2,"");
        System.out.println("dataArticle. = " + dataArticle.getArticle().toString());


    }
}
