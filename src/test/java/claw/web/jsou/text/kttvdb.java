package claw.web.jsou.text;

public class kttvdb {

    public kttvdb() {
        String homeLink = "http://kttvdb.net";
        String oldLink = "http://kttvdb.net/du-bao-khi-tuong/6310/dbtt-dem-28-ngay-29-thang-04-nam-2018.htm";
        String dataFolder = "D:/weather/kttvdb";
        String logFile = "/log.txt";

        String cssCollectorListLink = "#left_right_content > div:nth-child(5) > div > marquee > ul > li";
        String[] cssCollectorContent = new String[]{"#left_right_content > div > div > div > div.content_news_page"};

        new CloneContent(homeLink, oldLink, dataFolder, logFile).getData(cssCollectorListLink, cssCollectorContent);
    }
}
