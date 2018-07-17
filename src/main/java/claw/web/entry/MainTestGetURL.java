package claw.web.entry;


import claw.web.html.Html;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class MainTestGetURL {

    public static void main(String[] args) {
//        String homelink = "http://triethoc.edu.vn";
//        String css = "#all > div.bd > div > div.left-col > div";

        String homelink = "http://kenhthoitiet.am.local/";
        String css = "body";
        Html html = new Html();
        Document document = html.getDocument(homelink);
        Entry entry = new Entry();

        List<String> list2 = entry.getListHrefRecursive(document,css,"tin-tuc","/./");
        list2.forEach(x -> System.out.println(x));


    }
}
