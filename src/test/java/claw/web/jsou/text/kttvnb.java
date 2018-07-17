package claw.web.jsou.text;

public class kttvnb {

    public kttvnb() {
        String homeLink = "http://kttvnb.vn/";
        String dataListLink = "http://kttvnb.vn/file_weather/";
        String oldLink = "";
        String removeLink = homeLink;
        String dataFolder = "D:/weather/kttvnb/";
        String logFile = "log.txt";

        String cssCollectorListLink = "body > div.wrapper > section > div > div > div.col-md-11.col-sm-11 > div > div > div > div > div:nth-child(1) > a.read-more.pull-right";
        String[] cssCollectorContent = new String[]{"#document > div.title > h1",
                "#document > div.body > table"};

        new CloneContent(homeLink, dataListLink, oldLink, removeLink, dataFolder, logFile).getData(cssCollectorListLink, cssCollectorContent);
    }
}
