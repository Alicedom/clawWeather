package claw.web.jsou.text;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Ra2 {

    public Ra2() {

        String strURL = "http://ra2-nwp.kishou.go.jp";
        try{


            Document document = Jsoup.connect(strURL)
                    .header("Authorization", "Digest username=\"vietnam\", realm=\"RA2-NWP.KISHOU.GO.JP\", nonce=\"qxWx4VtuBQA=da82265e3a0a2004d0d5a3653042dc4443f2c529\", uri=\"/cityfc/VietNam/citySFC_VietNam_menu.html\", algorithm=MD5").get();


            System.out.println(document.outerHtml());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Ra2();
    }
}
