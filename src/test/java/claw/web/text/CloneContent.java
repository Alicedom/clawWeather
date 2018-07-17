package claw.web.text;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CloneContent {
    private String homeLink;
    private String dataListLink;
    private String oldLink;
    private String removeLink;
    private String dataFolder;
    private String logFile;


    public CloneContent(String initHomeLink, String initOldLink, String initDataFolder, String initLogFile) {
        this.homeLink = initHomeLink;
        this.dataListLink = homeLink;
        this.oldLink = initOldLink;
        this.removeLink = homeLink;
        this.dataFolder = initDataFolder;
        this.logFile = initLogFile;

    }

    public CloneContent(String initHomeLink, String dataListLink, String initOldLink, String initRemoveLink, String initDataFolder, String initLogFile) {
        this.homeLink = initHomeLink;
        this.dataListLink = dataListLink;
        this.oldLink = initOldLink;
        this.removeLink = initRemoveLink;
        this.dataFolder = initDataFolder;
        this.logFile = initLogFile;

    }

    public void getData(String cssCollectorListLink, String[] cssSelectorContent, String cssRemoveContent) {

        checkAndCreateNewFile(dataFolder + logFile);

        Document doc = getDocToLink(dataListLink);
        Elements liList = doc.select(cssCollectorListLink);
        System.out.println("Get number link: " + liList.size());

        int iFile = 0;
        for (; iFile < liList.size(); iFile++) {

            String link = liList.get(iFile).select("a").attr("href");
            if (!link.contains("http://") || !link.contains(homeLink))
                link = homeLink + link;
            System.out.println("Link data: " + link);

            if (link.equals(oldLink)) {
                break;
            } else {
                this.parseDataFromLink(link, cssSelectorContent, cssRemoveContent);
                try {
                    Files.write(Paths.get(dataFolder + logFile), link.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Number new file content: " + (iFile + 1));

    }

    public void getData(String cssCollectorListLink, String[] cssCollectorContent) {
        getData(cssCollectorListLink, cssCollectorContent, null);
    }


    private void parseDataFromLink(String link, String[] cssSelectorContent, String cssRemoveContent) {


        try {
            Document doc = getDocToLink(link);

            doc.select(cssRemoveContent).remove();

            for (String contentClass : cssSelectorContent) {
                Elements content = doc.select(contentClass);
                String cloneFile = dataFolder + link.replace(removeLink, "");
                if (!cloneFile.endsWith(".html") || !cloneFile.endsWith(".htm"))
                    cloneFile = cloneFile + ".html";

                checkAndCreateNewFile(cloneFile);
                Files.write(Paths.get(cloneFile), content.outerHtml().getBytes(), StandardOpenOption.APPEND);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Document getDocToLink(String link) {


        Document document = null;
        try {
            document = Jsoup.connect(link).timeout(30000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    private void checkAndCreateNewFile(String file) {
        File log = new File(file);

        if (!log.exists()) {
            try {
                System.out.println("Create: " + file);

                log.getParentFile().mkdirs();
                log.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
