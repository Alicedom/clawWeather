package claw.web.article;

import javax.swing.text.html.parser.Element;
import java.util.List;

public class Article{


    private String thumbnailURL;
    private String title;
    private String date;
    private String sapo;
    private String url;
    private String author;
    private String body;
    private List<String> imageBody;
    private List<String> videoBody;


    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSapo() {
        return sapo;
    }

    public void setSapo(String sapo) {
        this.sapo = sapo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getImageBody() {
        return imageBody;
    }

    public void setImageBody(List<String> imageBody) {
        this.imageBody = imageBody;
    }

    public List<String> getVideoBody() {
        return videoBody;
    }

    public void setVideoBody(List<String> videoBody) {
        this.videoBody = videoBody;
    }

    @Override
    public String toString() {
        return "Article{" +
                "thumbnailURL='" + thumbnailURL + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sapo='" + sapo + '\'' +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", imageBody=" + imageBody +
                ", videoBody=" + videoBody +
                '}';
    }
}
