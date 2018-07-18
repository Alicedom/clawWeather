package claw.web.article;

import claw.web.article.article.Article;

public class MainTest {

    public static void main(String[] args) {
        String homelink= "http://triethoc.edu.vn/vi/chuyen-de-triet-hoc/logic-hoc-tu-duy-phan-bien/hai-phong-cach-tu-duy-mieng-bot-bien-va-dai-cat-tim-vang_840.html";
        String css="#ctl00_Default1__Body > div.page-wrapper.page-wrapper2 > div > div.content-more";
        Article article = new Article();
        System.out.println("entry.toString() = " + article.toString());

    }
}
