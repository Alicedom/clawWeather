package claw.web.data;

import claw.web.entry.Entry;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Map;

/*
get full data
 */
public class DataArticle {
    Article article;

    public DataArticle(String url){
        article = new Article(url);
    }

    public DataArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public void getSourceFromElement() {
        List<String> listSrc = article.getSrcList();
        listSrc.addAll(Entry.getListSourceRecursive(article.getElement(), ""));
        article.setSrcList(listSrc);
    }

    /*
        get text from element
         */
    public void getParseText(Element e, Map<String, String> cssQueryText) {
        Map<String, String> listText = article.getTextList();
        if (e == null || !e.hasText()) {

        } else {
            cssQueryText.forEach((k, v) -> {
                String text = e.select(v).text();

                if (text == null || text.isEmpty()) {

                } else if (!listText.containsKey(k)) {
                    listText.put(k, text);
                } else if (listText.get(k) == null || listText.get(k).equals("")) {
                    listText.replace(k, text);
                }
            });

        }
    }

    /*
    get data body & src in body selected
     */
    public void getParseDataFromLink(Document document, List<String> cssSelectorContent, String cssRemoveContent) {
        Elements content = new Elements();
        List<String> listSrc = article.getSrcList();

        if (cssRemoveContent == null || cssRemoveContent.isEmpty()) {
            //pass css remove
        } else {
            document.select(cssRemoveContent).remove();
        }

        if (cssSelectorContent == null || cssSelectorContent.size() == 0) {

        } else {
            for (String contentClass : cssSelectorContent) {
                Elements elements = document.select(contentClass);

                listSrc.addAll(Entry.getListSourceRecursive(elements, ""));
                content.addAll(elements);
            }
        }

        article.setHtmlList(content);
        article.setSrcList(listSrc);
    }

    public void getParseDataFromLink(Document document, String cssSelectorContent, String cssRemoveContent) {
        Elements content = new Elements();
        List<String> listSrc = article.getSrcList();

        if (cssRemoveContent.isEmpty()) {

        } else {
            document.select(cssRemoveContent).remove();
        }

        Elements elements = document.select(cssSelectorContent);

        article.setHtmlList(content);
        article.setSrcList(listSrc);
    }
}
