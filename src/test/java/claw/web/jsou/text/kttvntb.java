package claw.web.jsou.text;

public class kttvntb {

    public kttvntb() {
        String homeLink = "http://kttvntb.gov.vn/";
        String dataListLink = "http://kttvntb.gov.vn/Doc.aspx?f=6";
        String oldLink = "";
        String removeLink = "http://kttvntb.gov.vn/Doc.aspx?f=";
        String dataFolder = "D:/weather/kttvntb/";
        String logFile = "log.txt";

        String cssCollectorListLink = "#doclist > div > h2";
        String[] cssCollectorContent = new String[]{"#document > div.title > h1",
                "#document > div.body > table"};

        new CloneContent(homeLink, dataListLink, oldLink, removeLink, dataFolder, logFile).getData(cssCollectorListLink, cssCollectorContent);
    }
}
