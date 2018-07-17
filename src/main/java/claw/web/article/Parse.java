package claw.web.article;

import claw.web.html.Html;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
get full data from url
clean data
 */
public class Parse{
    private Article article;
    private Element element;

    public Parse() {


    }

    private void setElement(Element element){
        if (article == null) {
            article = new Article();
        }
    }


    private String setUrl(String urlCssQuery) {
        String url = null;

        if (urlCssQuery != null && article.getUrl() == null) {
            url = element.select(urlCssQuery).select("a").attr("abs:href");
        }
        article.setUrl(url);
        return url;
    }

    private String setThumbnailURL(String thumbnailURLCSSQuery) {
        String thumbnail = null;

        if (thumbnailURLCSSQuery != null && article.getThumbnailURL() == null) {
            thumbnail = element.select(thumbnailURLCSSQuery).select("img").attr("abs:src");
        }

        article.setThumbnailURL(thumbnail);
        return thumbnail;
    }

    private String setTitle(String titleCSSQuery) {
        String title = null;

        if (titleCSSQuery != null && article.getTitle() == null) {
            title = element.select(titleCSSQuery).text();
        }

        article.setTitle(title);
        return title;
    }

    private String setDate(String dateCSSQuery) {
        String date = null;

        if (dateCSSQuery != null && article.getDate() == null) {
            date = element.select(dateCSSQuery).text();
        }

        article.setDate(date);

        return date;
    }

    private String setSapo(String sapoCSSQuery) {
        String sapo = null;

        if (sapoCSSQuery != null && article.getSapo() == null) {
            sapo = element.select(sapoCSSQuery).text();
        }

        article.setSapo(sapo);

        return sapo;
    }

    private String setAuthor(String authorCSSQuery) {
        String author = null;

        if (authorCSSQuery != null && article.getAuthor() == null) {
            author = element.select(authorCSSQuery).text();
        }

        article.setAuthor(author);

        return author;
    }

    private String setBody(String bodyCSSQuery, String cssRemoveContent) {
        String body = null;

        if (bodyCSSQuery != null && article.getBody() == null) {

            Elements contentBody = element.select(bodyCSSQuery);
            Elements contentOutOfRemoved = contentBody.select(cssRemoveContent).remove();

            body = transformURLInBody(contentOutOfRemoved.outerHtml(), null, null);
        }

        article.setBody(body);

        return bodyCSSQuery;
    }

    private String setBody(String bodyCSSQuery, String cssRemoveContent, String urlSource, String urlTarget) {
        String body = null;

        if (bodyCSSQuery != null && article.getBody() == null) {

            Elements contentBody = element.select(bodyCSSQuery);
            Elements contentOutOfRemoved = contentBody.select(cssRemoveContent).remove();

            if(urlSource != null && !urlSource.equals("")){
                body = transformURLInBody(contentOutOfRemoved.outerHtml(), urlSource, urlTarget);
            }else{
                body = contentOutOfRemoved.html();
            }

        }

        article.setBody(body);

        return bodyCSSQuery;
    }

    private String transformURLInBody(String body, String urlSource, String urlTarget) {
        return body.replace(urlSource, urlTarget);
    }

}
