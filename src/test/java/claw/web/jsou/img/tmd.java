package claw.web.jsou.img;

public class tmd {
    public tmd() {
        String homeLink = "https://web.kma.go.kr/eng/weather/images/";
        String dataListLink = "https://web.kma.go.kr/eng/weather/images/analysischart.jsp";
        String homeImageLink = "https://web.kma.go.kr/";
        String oldLink = "";
        String removeLink = "https://web.kma.go.kr//repositary/image/cht/";
        String dataFolder = "D:/weather/kma/";
        String logFile = "/log.txt";

        String cssCollectorListLink = "#contents > div.distibution_search3.MB20 > form > fieldset.town_search > ul > li";
        String cssSelectorImage = "#contents > div:nth-child(2) > ul > li > img";

        new CloneImage(homeLink, dataListLink, homeImageLink, oldLink, removeLink, dataFolder, logFile).getData(cssCollectorListLink, cssSelectorImage);
    }
}
