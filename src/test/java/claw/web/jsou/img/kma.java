package claw.web.jsou.img;

public class kma {
    public kma() {

        String homeLink = "https://www.tmd.go.th";
        String dataListLink = "https://web.kma.go.kr/eng/weather/images/analysischart.jsp";
        String oldLink = "";
        String removeLink = "https://www.tmd.go.th/programs//uploads/";
        String dataFolder = "D:/weather/tmd5/";
        String logFile = "/log.txt";

        String cssCollectorListLink = "#contents > div.distibution_search3.MB20 > form > fieldset.town_search > ul > li";

        new CloneImage(homeLink, dataListLink, oldLink, removeLink, dataFolder, logFile).getData(cssCollectorListLink);
    }
}
