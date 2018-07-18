package claw.web.html;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
/*
get html from many type of connect
 */
public class Html {
    public static final String METHOD_POST = "post";
    public static final String METHOD_GET = "get";

    /*
      Document doc = Jsoup.connect("http://example.com")
      .data("query", "Java")
      .userAgent("Mozilla")
      .cookie("auth", "token")
      .timeout(3000)
      .post();
     */

    public static Document getDocument(String url){
        return getDocument(url, null, null, 0, null);
    }

    public static Document getDocument(String url, int timeout){
        return getDocument(url, null, null, timeout, null);
    }

    public static Document getDocument(String url, String userAgent, HashMap cookies, int timeout, String method) {
        Document document = null;

        if (url != null) {
            Connection connection = Jsoup.connect(url);

            if (userAgent != null && ! userAgent.equals("")) {
                connection.userAgent(userAgent);
            }

            if (cookies != null) {
                connection.cookies(cookies);
            }

            if (timeout > 0) {
                connection.timeout(0);
            }

            try {
                if (method == null || method.compareToIgnoreCase(METHOD_GET) == 0) {
                    document = connection.get();
                } else {
                    document = connection.post();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("document.childNodeSize() = " + document.childNodeSize());
        return document;
    }


}
