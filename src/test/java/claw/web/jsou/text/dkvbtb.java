package claw.web.jsou.text;

public class dkvbtb {
    public dkvbtb() {
        String homeLink = "http://dkvbtb.gov.vn/";
        String dataListLink = "http://dkvbtb.gov.vn/du-bao-2.html";
        String oldLink = "http://dkvbtb.gov.vn/du-bao/ban-tin-du-bao-hai-van-tu-ngay-19-5-–-28-5-2018-723.html";
        String removeLink = "http://dkvbtb.gov.vn/";
        String dataFolder = "D:/weather/dkvbtb/";
        String logFile = "/log.txt";

        String cssCollectorListLink = "body > div.wrapper_div.container > div.content > div > div > div.col-lg-6.col-md-6.col-sm-6.main-content.post-list > div.row > div > h2";
        String[] cssCollectorContent = new String[]{"body > div.wrapper_div.container > div.content > div > div > div.col-lg-6.col-md-6.col-sm-6.main-content > div.std"};

        new CloneContent(homeLink, dataListLink, oldLink, removeLink, dataFolder, logFile).getData(cssCollectorListLink, cssCollectorContent);

    }
}
