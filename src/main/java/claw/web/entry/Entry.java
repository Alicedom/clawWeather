package claw.web.entry;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;
/*
get link a href/ link src from html
 */
public class Entry {

    public List<String> getListSource(Element element, String cssSelector){
        return getListSource(element,cssSelector,null,null);
    }

    public List<String> getListSource(Element element, String cssSelector, String include, String exclude) {
        List<String> listSource = new LinkedList<>();

        if (cssSelector != null) {
            element.select(cssSelector).forEach(y -> {
                Elements elements = y.select("[src]");
                elements.forEach(x->{
                    String url = x.attr("src");
                    if (url == null
                            || (include != null && !include.equals("") && !url.contains(include))
                            || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

                    }else{
                        listSource.add(url);
                    }
                });
            });
        }

        System.out.println("listSource.size() = " + listSource.size());

        return listSource;
    }

    public List<String> getListHrefRecursive(Element element, String cssSelector){
        return getListHrefRecursive(element,cssSelector,null,null);
    }

    public List<String> getListHrefRecursive(Element element, String cssSelector, String include, String exclude) {
        List<String> listLink = new LinkedList<>();

        if (cssSelector != null) {
            element.select(cssSelector).forEach(x -> {
                Elements elements = x.select("a");
                System.out.println("elements.size() = " + elements.size());
                elements.forEach(y -> {
                    String url = y.attr("abs:href");
                    if (url == null
                            || (include != null && !include.equals("") && !url.contains(include))
                            || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

                    }else{
                        listLink.add(url);
                    }
                });
            });
        }

        System.out.println("listLink.size() = " + listLink.size());
        return listLink;
    }

}
