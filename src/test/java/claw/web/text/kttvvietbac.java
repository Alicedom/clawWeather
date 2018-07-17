package claw.web.text;

public class kttvvietbac {

    public kttvvietbac() {

        String homeLink = "http://kttvvietbac.org";
        String dataListLink = "http://kttvvietbac.org/dai/index.php?language=vi&nv=news&op=Tin-tuc";
        String oldLink = "http://kttvvietbac.org/dai/index.php?language=vi&nv=news&op=Tin-tuc/Du-bao-thoi-tiet-Ngay-va-dem-09-04-2018-1239";
        String removeLink = "http://kttvvietbac.org/dai/index.php?language=vi&nv=news&op=";
        String dataFolder = "D:/weather/kttvvietbac/";
        String logFile = "/log.txt";

        String cssCollectorListLink = "body > div.wrapper > div:nth-child(2) > div:nth-child(3) > div.col-b1 > div > div > h4";
        String[] cssCollectorContent = new String[]{"body > div.wrapper > div:nth-child(2) > div:nth-child(3) > div.col-b1 > div.header-details > div.title",
                "#bodytext"};

        new CloneContent(homeLink, dataListLink, oldLink, removeLink, dataFolder, logFile).getData(cssCollectorListLink, cssCollectorContent);
    }
}
