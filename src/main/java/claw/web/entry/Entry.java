package claw.web.entry;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/*
get all  link a href and link src from html
(recursive) : an element of html can give many link/url
 */
public class Entry {

    public static List<String> getListSourceRecursive(Elements element, String cssSelector) {
        return getListSourceRecursive(element, cssSelector, null, null);
    }

    public static List<String> getListSourceRecursive(Elements element, String cssSelector, String include, String exclude) {
        List<String> listSource = new LinkedList<>();

        if (cssSelector.isEmpty()) {
            Elements elements = element.select("[src]");
            elements.forEach(x -> {
                String url = x.attr("src");
                if (url == null
                        || (include != null && !include.equals("") && !url.contains(include))
                        || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

                } else {
                    listSource.add(url);
                }
            });
        } else {
            element.select(cssSelector).forEach(y -> {
                Elements elements = y.select("[src]");
                elements.forEach(x -> {
                    String url = x.attr("src");
                    if (url == null
                            || (include != null && !include.equals("") && !url.contains(include))
                            || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

                    } else {
                        listSource.add(url);
                    }
                });
            });

        }

        System.out.println("listSource.size() = " + listSource.size());

        return listSource;
    }

    public static List<String> getListSourceRecursive(Element element, String cssSelector) {
        return getListSourceRecursive(element, cssSelector, null, null);
    }

    public static List<String> getListSourceRecursive(Element element, String cssSelector, String include, String exclude) {
        List<String> listSource = new LinkedList<>();

        if (cssSelector.isEmpty()) {
            System.out.println("cssSelector to get source = null");
            Elements elements = element.select("[src]");
            elements.forEach(x -> {
                String url = x.attr("abs:src");
                if (url == null
                        || (include != null && !include.equals("") && !url.contains(include))
                        || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

                } else {
                    listSource.add(url);

                }
            });
        } else {
            System.out.println("cssSelector to get source not null");
            element.select(cssSelector).forEach(y -> {
                Elements elements = y.select("[src]");
                elements.forEach(x -> {
                    String url = x.attr("abs:src");
                    if (url == null
                            || (include != null && !include.equals("") && !url.contains(include))
                            || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

                    } else {
                        listSource.add(url);
                    }
                });
            });

        }

        System.out.println("listSource.size() = " + listSource.size());

        return listSource;
    }

    public static List<String> getListHrefRecursive(Element element, String cssSelector) {
        return getListHrefRecursive(element, cssSelector, null, null);
    }

    public static List<String> getListHrefRecursive(Element element, String cssSelector, String include, String exclude) {
        List<String> listLink = new LinkedList<>();

        if (cssSelector.isEmpty()) {
            Elements elements = element.select("a");
            System.out.println("elements.size() = " + elements.size());
            elements.forEach(y -> {
                String url = y.attr("abs:href");
                if (url == null
                        || (include != null && !include.equals("") && !url.contains(include))
                        || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

                } else {
                    listLink.add(url);
                }
            });
        } else {

            element.select(cssSelector).forEach(x -> {
                Elements elements = x.select("a");
                System.out.println("elements.size() = " + elements.size());
                elements.forEach(y -> {
                    String url = y.attr("abs:href");
                    if (url == null
                            || (include != null && !include.equals("") && !url.contains(include))
                            || (exclude != null && !exclude.equals("") && url.contains(exclude))) {

                    } else {
                        listLink.add(url);
                    }
                });
            });

        }

        System.out.println("listLink.size() = " + listLink.size());
        return listLink;
    }

}
