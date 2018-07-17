package claw.web.media;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Media implements IMedia {
    private void downloadImageFromLink(String link, String cloneFile) {
        Runnable r = new DownloadImage1(link, cloneFile);
        new Thread(r).start();

    }

    @Override
    public void creteThread() {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        threadPool.submit(new DownloadImage1(link, clo));
    }

    private class DownloadImage1 implements Runnable {

        private String link;
        private String cloneFile;
        public DownloadImage1(String link, String cloneFile) {
            this.link=link;
            this.cloneFile = cloneFile;
        }

        public void run() {
            URL url = null;
            try {
                url = new URL(link);

                InputStream in = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();

                FileOutputStream fos = new FileOutputStream(cloneFile);
                fos.write(response);
                fos.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
