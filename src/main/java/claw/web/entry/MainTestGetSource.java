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
        List<String> list2 = Entry.getListSourceRecursive(doc,css);
        list2.forEach(System.out::println);

        System.out.println("list2.size() = " + list2.size());


    }
}
