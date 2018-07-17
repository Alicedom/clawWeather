package claw.web.jsou.text;

public class kttvttb  {

    public kttvttb() {

        String homeLink = "http://kttvttb.vn/";
        String dataListLink = "http://kttvttb.vn/news/khi-tuong.html";
        String oldLink = null;//"http://kttvttb.vn/news/khi-tuong/bao-so-13-do-bo-bien-dong-chuyen-gia-thong-tin-thoi-tiet-da-nang-dip-apec-615";
        String removeLink = "http://kttvttb.vn/news/";
        String dataFolder = "D:/weather/kttvttb/";
        String logFile = "log.txt";

        String cssCollectorListLink = "#main-content > div.wrapper > div > div > div.l-col.widget-area-6 > div.row-fluid > div > ul > li > article > div.entry-content > header > h4";
        String[] cssCollectorContent = new String[]{"#main-content > div.wrapper > div > div > div.l-col.widget-area-6 > div.row-fluid > div > div.entry-box.clearfix"};
        String cssCollectorRemoveContent = "script,.hidden,form, div.addthis_toolbox.addthis_default_style, footer";

        new CloneContent(homeLink, dataListLink, oldLink, removeLink, dataFolder, logFile).getData(cssCollectorListLink, cssCollectorContent, cssCollectorRemoveContent);
    }
}
