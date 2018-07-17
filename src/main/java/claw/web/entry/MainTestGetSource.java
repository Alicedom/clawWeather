package claw.web.entry;

import claw.web.html.Html;
import org.jsoup.nodes.Document;

import java.util.List;

public class MainTestGetSource {
    public static void main(String[] args) {
        String homelink = "http://kenhthoitiet.am.local/tin-tuc/thoi-tiet-khoa-hoc/nhung-con-mua-ky-la-nhat-trong-lich-su-101649/";
        String css = "body";

        Html html = new Html();
        Document doc = html.getDocument(homelink);

        Entry entry = new Entry();
        List<String> list2 = entry.getListSource(doc,css);

        list2.forEach(e -> System.out.println(e));


    }
}
