package claw.web.article;

import claw.web.article.article.ParseArticle;

public class MainParseTest {
    public static void main(String[] args) {
        String homelink= "http://triethoc.edu.vn/vi/chuyen-de-triet-hoc/triet-hoc-nhan-hoc/";
        String css="#ctl00_Default1__Body > div > div.page-wrapper > div > div.content-list > ul > li:nth-child(1)";
        String cssThumbnail="#ctl00_Default1__Body > div > div.page-wrapper > div > div.content-list > ul > li:nth-child(1) > div > a > img";
        String cssURL = "#ctl00_Default1__Body > div > div.page-wrapper > div > div.content-list > ul > li:nth-child(1) > div > a";
        String cssDate="#ctl00_Default1__Body > div > div.page-wrapper > div > div.content-list > ul > li:nth-child(1) > div > span";
        String cssSapo="#ctl00_Default1__Body > div > div.page-wrapper > div > div.content-list > ul > li:nth-child(1) > div > p";
//        ParseArticle article = new ParseArticle();
//        article.setUrlCssQuery(cssURL);
//        article.setDateCSSQuery(cssDate);
//        article.setSapoCSSQuery(cssSapo);
//        article.setThumbnailURLCSSQuery(cssThumbnail);

    }
}
