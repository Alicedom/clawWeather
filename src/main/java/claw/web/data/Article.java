package claw.web.data;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class Article {
    private String url; //url from short html
    private Element element; // short html from category
    private Map<String, String> textList; // title, sapo, date, author, tag. category???
    private List<String> srcList; // url src. feature image is first img in list
    private Map<String,String> hrefList; // href in tagname/ text referance
    private Elements htmlList; //body content

    public Article(String url) {
        this.url = url;
        textList = new TreeMap<>();
        srcList = new LinkedList<>();
        htmlList = new Elements();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Map<String, String> getTextList() {
        return textList;
    }

    public void setTextList(Map<String, String> textList) {
        this.textList = textList;
    }

    public List<String> getSrcList() {
        return srcList;
    }

    public void setSrcList(List<String> srcList) {
        this.srcList = srcList;
    }

    public Elements getHtmlList() {
        return htmlList;
    }

    public void setHtmlList(Elements htmlList) {
        this.htmlList = htmlList;
    }

    @Override
    public String toString() {
        return "DataArticle{" +
                "url='" + url + '\'' +
                ", textList=" + textList +
                ", element=" + element +
                ", htmlList=" + htmlList +
                ", srcList=" + srcList +
                ", hrefList=" + hrefList +
                '}';
    }
}
