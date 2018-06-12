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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DownloadThreadPool {
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

        ArrayBlockingQueue<Runnable> listRunnable = new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 12,
                TimeUnit.SECONDS, listRunnable);

        for (int i = 0; i < list.size(); i = i + THREAD_SIZE) {
            Runnable r = new DownloadFileThread(list.get(i), String.valueOf(i), SAVE_FOLDER, TIME_OUT);
            threadPoolExecutor.execute(r);
        }

        try {
            Thread.sleep(THREAD_SIZE * TIME_OUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*
    Choose first: calculate time to get URL
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
    choose second:  take last url using jsoup
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

        private String link;
        private String fileName;
        private String folder;
        private int timeout;

        public DownloadFileThread(String link,String i, String folder, int timeout) {
            this.link = link;
            this.fileName = i;
            this.folder = folder;
            this.timeout = timeout;
        }

        public void run() {

            try {

//                    func(link,  folder + fileName);
                    func2(link, folder + fileName);

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
