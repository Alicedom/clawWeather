package claw.web;

import claw.web.entry.Entry;
import claw.web.html.Html;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class TestCssQueryMultiselector {
    public static void main(String[] args) {
        String homelink ="http://kenhthoitiet.am.local/box/featured/lo-lang-khong-khi-lanh-tran-xuong-nam-bo-chuyen-gia-thoi-tiet-tran-an-101789/";


        String css = "#post-101789 > div.td-post-content > header > p > b," +
                "#post-101789 > div.td-post-header > header > h1," +
                "#post-101789 > div.td-post-header > header > div > span > time," +
                "#post-101789 > footer > div.td-post-source-tags > div > div > a";
        Html html = new Html();
        Document document = html.getDocument(homelink);
        Elements elements = document.select(css);
        elements.forEach(x -> System.out.println(x));

    }
}
