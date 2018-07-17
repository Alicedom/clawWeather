package claw.web.jsou.text;

public class kttvtaynguyen {
    public kttvtaynguyen() {
        String homeLink = "http://kttvtaynguyen.org.vn";
        String dataListLink = "http://kttvtaynguyen.org.vn/daitn/index.php?language=vi&nv=news&op=Du-bao-thoi-tiet-trong-24h";
        String oldLink = "";
        String removeLink = "http://kttvtaynguyen.org.vn/daitn/index.php?language=vi&nv=news&op=";
        String dataFolder = "D:/weather/kttvtaynguyen/";
        String logFile = "log.txt";

        String cssCollectorListLink = "body > div.wrapper > div:nth-child(2) > div:nth-child(3) > div.col-a1 > div > div > h4";
        String[] cssCollectorContent = new String[]{"body > div.wrapper > div:nth-child(2) > div:nth-child(3) > div.col-a1 > div.header-details > div.title",
                "#bodytext"};

        new CloneContent(homeLink, dataListLink, oldLink, removeLink, dataFolder, logFile).getData(cssCollectorListLink, cssCollectorContent);
    }
}
