package claw.web.learnforfun;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class Clone {

    private Map<String, String> cookies = null;



    public Clone(){

        Connection.Response res = null;
        try {
            res = Jsoup
                    .connect("http://www.learnforfun.vn/vi/mobilelogin")
                    .userAgent("Mozilla/5.0")
                    .validateTLSCertificates(true)
                    .data("name", "name")
                    .data("form_build_id", "form-aqZBxU2J3PNJQahl9cg6ruSiQvf1UDBVW-N2zid2Z20")
                    .data("form_id", "msisdn_login_form")
                    .method(Connection.Method.POST)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //This will get you cookies
        cookies = res.cookies();
        cookies.forEach((item, b) -> System.out.println(item + " => " + b));
    }



    public void getData(String link, int startId, int endId, String folder) {


        for (int i = startId; i <= endId; i++) {

            try {

                Document doc = Jsoup.connect(link + i).userAgent("Mozilla/5.0")
                        .validateTLSCertificates(true).cookies(cookies).get();


                String htmlFile = folder+ i+"/"+i+".html";
                checkAndCreateNewFile( htmlFile);
                Files.write(Paths.get(htmlFile), doc.toString().getBytes(), StandardOpenOption.APPEND);


                downloadAllSource(doc,folder+ i+"/","http://www.learnforfun.vn/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void downloadAllSource(Document doc, String saveFolderPath, String homeLink) {

        Elements images = doc.select("[src]");
        for (Element image: images) {
            String fileURL = image.absUrl("src");

            if(fileURL.contains(".mp3") || fileURL.contains(".jpg") || fileURL.contains(".png")){
                if(! fileURL.contains(homeLink)){
                    fileURL= homeLink +fileURL;

                }
                if (fileURL.contains("?")){
                    fileURL=fileURL.substring(0,fileURL.indexOf("?"));
                }
                System.out.println(fileURL);
                String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,fileURL.length());
                checkAndCreateNewFile(saveFolderPath+fileName);
                Runnable r = new DownloadFile(fileURL, saveFolderPath+fileName);
                new Thread(r).start();
            }

        }

    }
    private class DownloadFile implements Runnable {

        private String link;
        private String cloneFile;

        public DownloadFile(String link, String cloneFile) {
            this.link = link;
            this.cloneFile = cloneFile;
        }

        public void run() {
            URL url = null;
            try {
                url = new URL(link);

                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(cloneFile);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
