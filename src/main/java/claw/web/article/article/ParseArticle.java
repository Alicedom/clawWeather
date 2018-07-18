package claw.web.article.article;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
get full data from url
clean data
 */
public class ParseArticle {
    private Article article;
    private Element element;
    private String urlCssQuery;
    private String thumbnailURLCSSQuery;
    private String titleCSSQuery;
    private String dateCSSQuery;
    private String sapoCSSQuery;
    private String authorCSSQuery;
    private String bodyCSSQuery;
    private String cssRemoveContent;
    private String urlSource;
    private String urlTarget;

    public ParseArticle(Element element) {
        article = new Article();
        this.element = element;
    }

    private String getUrl() {
        String url = null;

        if (urlCssQuery != null && article.getUrl() == null) {
            url = element.select(urlCssQuery).select("a").attr("abs:href");
        }
        article.setUrl(url);
        return url;
    }

    private String getThumbnailURL() {
        String thumbnail = null;

        if (thumbnailURLCSSQuery != null && article.getThumbnailURL() == null) {
            thumbnail = element.select(thumbnailURLCSSQuery).select("img").attr("abs:src");
        }

        article.setThumbnailURL(thumbnail);
        return thumbnail;
    }

    private String getTitle() {
        String title = null;

        if (titleCSSQuery != null && article.getTitle() == null) {
            title = element.select(titleCSSQuery).text();
        }

        article.setTitle(title);
        return title;
    }

    private String getDate() {
        String date = null;

        if (dateCSSQuery != null && article.getDate() == null) {
            date = element.select(dateCSSQuery).text();
        }

        article.setDate(date);

        return date;
    }

    private String getSapo() {
        String sapo = null;

        if (sapoCSSQuery != null && article.getSapo() == null) {
            sapo = element.select(sapoCSSQuery).text();
        }

        article.setSapo(sapo);

        return sapo;
    }

    private String getAuthor() {
        String author = null;

        if (authorCSSQuery != null && article.getAuthor() == null) {
            author = element.select(authorCSSQuery).text();
        }

        article.setAuthor(author);

        return author;
    }

    private String getBody() {
        String body = null;

        if (bodyCSSQuery != null && article.getBody() == null) {

            Elements contentBody = element.select(bodyCSSQuery);
            Elements contentOutOfRemoved = contentBody.select(cssRemoveContent).remove();

            if (urlSource != null && !urlSource.equals("")) {
                body = transformURLInBody(contentOutOfRemoved.outerHtml(), urlSource, urlTarget);
            } else {
                body = contentOutOfRemoved.html();
            }

        }

        article.setBody(body);

        return bodyCSSQuery;
    }

    private String transformURLInBody(String body, String urlSource, String urlTarget) {
        return body.replace(urlSource, urlTarget);
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void setUrlCssQuery(String urlCssQuery) {
        this.urlCssQuery = urlCssQuery;
    }

    public void setThumbnailURLCSSQuery(String thumbnailURLCSSQuery) {
        this.thumbnailURLCSSQuery = thumbnailURLCSSQuery;
    }

    public void setTitleCSSQuery(String titleCSSQuery) {
        this.titleCSSQuery = titleCSSQuery;
    }

    public void setDateCSSQuery(String dateCSSQuery) {
        this.dateCSSQuery = dateCSSQuery;
    }

    public void setSapoCSSQuery(String sapoCSSQuery) {
        this.sapoCSSQuery = sapoCSSQuery;
    }

    public void setAuthorCSSQuery(String authorCSSQuery) {
        this.authorCSSQuery = authorCSSQuery;
    }

    public void setBodyCSSQuery(String bodyCSSQuery) {
        this.bodyCSSQuery = bodyCSSQuery;
    }

    public void setCssRemoveContent(String cssRemoveContent) {
        this.cssRemoveContent = cssRemoveContent;
    }

    public void setUrlSource(String urlSource) {
        this.urlSource = urlSource;
    }

    public void setUrlTarget(String urlTarget) {
        this.urlTarget = urlTarget;
    }
}
