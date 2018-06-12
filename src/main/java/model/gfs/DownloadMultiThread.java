package model.gfs;


import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DownloadMultiThread {
    private final String LOG = "src/test/logDownload.txt";
    private final String SAVE_FOLDER = "src/test/file/Download/";
    private final int NUMBER_LINK = 20;
    private final int THREAD_SIZE = 5;
    private final int MIN_SIZE = 2500000;
    private final int TIME_OUT = 5000;


    public static void main(String[] args) {
        long start, stop;
        start = System.currentTimeMillis();
        new DownloadMultiThread().download();
        stop = System.currentTimeMillis();

        System.out.println("main" + (stop - start));
    }


    public void download() {
        List<String> list = getFileFromURL(NUMBER_LINK);

        for (int i = 0; i < list.size(); i = i + THREAD_SIZE) {
            Runnable r = new DownloadFileThread(list, i, THREAD_SIZE, SAVE_FOLDER, TIME_OUT);
            new Thread(r).start();
        }


        try {
            Thread.sleep(THREAD_SIZE * TIME_OUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int numberReDownload;
        do {
            numberReDownload = 0;

            for (int i = 0; i < list.size(); i++) {
                numberReDownload += checkToRedownloadFile(list.get(i), i, SAVE_FOLDER, MIN_SIZE);
                System.out.println("numberReDownload = " + numberReDownload);
            }
            try {
                System.out.println("Start re download in: "+ numberReDownload* TIME_OUT);
                Thread.sleep(numberReDownload* TIME_OUT); //? Lan dau se rat lau, lan sau se khong lau bang nhu the
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (numberReDownload > 0);

    }

    private int checkToRedownloadFile(String link, int i, String save_folder, int minSize) {
        File file = new File(save_folder + i);
        int reDown = 0;

        System.out.println("\nfile.getName() = " + file.getName());
        if (!file.exists() || file.length() < minSize) {
            System.out.println("file do not exist");
            reDown = 1;

        } else {
            System.out.println("file.length() = " + file.length());
            System.out.println("file.lastModified() = " + file.lastModified());
        }

        return reDown;
    }

    /*
    Choose first: calculate time to get URL
    1 session = 6 hour
        later VN than 7 h UTC + 4 h update data = 11h
        hour forecast using 3 number = 001 -> 120

    Phức tạp, dễ lỗi, nhanh: Toàn bộ link tính toán mất 33ms
     */
    public List<String> calculateDownload() {
        List<String> downloadLink = new LinkedList<String>();

        String fileURL = "http://nomads.ncep.noaa.gov/cgi-bin/filter_gfs_0p25.pl?file=gfs.tYYYz.pgrb2.0p25.f000" +
                "&lev_10_m_above_ground=on&lev_2_m_above_ground=on&lev_surface=on&var_APCP=on&var_RH=on&var_TMP=on&var_UGRD=on&var_VGRD=on" +
                "&leftlon=0&rightlon=360&toplat=90&bottomlat=-90&dir=%2Fgfs.XXX";

        Calendar calendar = Calendar.getInstance();
        int aroundHourInLastSession = calendar.get(Calendar.HOUR) / 6 * 6; // around last session
        calendar.set(Calendar.HOUR, aroundHourInLastSession);

        DateFormat dateformat = new SimpleDateFormat("yyyyMMddHH");
        String lastDir = dateformat.format(calendar.getTimeInMillis() - 12 * 3600 * 1000); // back around 2 session = 12h to take data

        fileURL = fileURL
                .replace("XXX", lastDir) // set dir
                .replace("YYY", lastDir.substring(8, 10)); // set file

        // calculate forecast hour
        DecimalFormat myFormatter = new DecimalFormat("f000");
        for (int i = 0; i <= 120; i++) {
            String output = myFormatter.format(i);
            String url = fileURL.replace("f000", output); // set hour on file
            downloadLink.add(url);
        }

        return downloadLink;
    }

    /*
    choose second:
    take last url using jsoup

    Đơn giản, chính xác, chậm: toàn bộ link mất 3000ms
     */
    public List<String> getFileFromURL(int numberData) {
        List<String> downloadLink = new LinkedList<String>();

        final String urlDir = "http://nomads.ncep.noaa.gov/cgi-bin/filter_gfs_0p25.pl";
        final String urlPartFile = "http://nomads.ncep.noaa.gov/cgi-bin/filter_gfs_0p25.pl?dir=%2F";
        String fileURL = "http://nomads.ncep.noaa.gov/cgi-bin/filter_gfs_0p25.pl?file=YYY" +
                "&lev_10_m_above_ground=on&lev_2_m_above_ground=on&lev_surface=on&var_APCP=on&var_RH=on&var_TMP=on&var_UGRD=on&var_VGRD=on" +
                "&leftlon=0&rightlon=360&toplat=90&bottomlat=-90&dir=%2FXXX";


        Elements elems = null;
        try {
            Document doc = Jsoup.connect(urlDir).timeout(10000).get();
            String lastDir = doc.select("table > tbody > tr > td > a").get(1).text().trim(); // back to second link
            fileURL = fileURL.replace("XXX", lastDir); // set dir

            doc = Jsoup.connect(urlPartFile + lastDir).timeout(10000).get();
            elems = doc.select("body > form > p:nth-child(2) > select > option");

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (elems.size() < numberData) {
            System.out.println("Do not enough data: " + elems.size());
        } else {

            for (int i = 0; i <= numberData; i++) {
                String file = elems.get(i).attr("value");
                String urlDownload = fileURL.replace("YYY", file); // set file
                downloadLink.add(urlDownload);
            }
        }

        return downloadLink;
    }

    private void checkAndCreateNewFile(String saveDir) {
        File log = new File(saveDir);

        if (log.getParentFile() != null && !log.getParentFile().exists()) {
            log.getParentFile().mkdirs();
        }
        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private class DownloadFileThread implements Runnable {

        private List<String> link;
        private int startLink;
        private int numberLinkPerThread;
        private String folder;
        private int timeout;

        public DownloadFileThread(List<String> list, int startLink, int numberLinkPerThread, String folder, int timeout) {
            this.link = list;
            this.startLink = startLink;
            this.numberLinkPerThread = numberLinkPerThread;
            this.folder = folder;
            this.timeout = timeout;
        }

        public void run() {

            try {
                int stopLink = link.size() < startLink + numberLinkPerThread ? link.size() : startLink + numberLinkPerThread;

                for (int i = startLink; i < stopLink; i++) {
//                    func(link.get(i), folder+i);
                    func2(link.get(i), folder + i);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //  use java.nio for buffer and chanel for download
        private void func2(String url, String file) throws MalformedURLException, IOException {
            long start, stop;
            start = System.currentTimeMillis();

            checkAndCreateNewFile(LOG);

            FileUtils.copyURLToFile(new URL(url), new File(file), timeout, timeout);

            stop = System.currentTimeMillis();
            String logInfo = String.valueOf((stop - start) + " : " + file + " : " + link + "\n");
            Files.write(Paths.get(LOG), logInfo.getBytes(), StandardOpenOption.APPEND);
            System.out.println(logInfo);
        }


        //  use java.nio for buffer and chanel for download
        private void func(String url, String file) throws MalformedURLException, IOException {
            long start, stop;
            start = System.currentTimeMillis();

            checkAndCreateNewFile(LOG);

            ReadableByteChannel rbc = Channels.newChannel(new URL(url).openStream());
            checkAndCreateNewFile(file);
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            stop = System.currentTimeMillis();
            String logInfo = String.valueOf((stop - start) + " : " + file + " : " + link + "\n");
            Files.write(Paths.get(LOG), logInfo.getBytes(), StandardOpenOption.APPEND);
            System.out.println(logInfo);
        }
    }

}
