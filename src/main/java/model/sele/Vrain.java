package model.sele;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Vrain {

    BufferedWriter log;

    public static void main(String[] args) {

        new Vrain().getFullData();
    }

    public void getFullData() {
        String exePath = "lib\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);

        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        try {
            login(driver);
            getTitleTime(driver);
            getListData(driver);
            closeDriver(driver);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void login(WebDriver driver) throws IOException, InterruptedException {
        String file = "weather\\vrain\\" + System.currentTimeMillis() + ".txt";

        Path logfile = Paths.get(file);
        if (!logfile.toFile().exists()) {
            try {
                System.out.println("Create: " + file);
                logfile.toFile().getParentFile().mkdirs();
                logfile.toFile().createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log = Files.newBufferedWriter(logfile);

        // login
        driver.get("https://vrain.vn/#/country");
        WebElement form = driver.findElement(By.xpath("//*[@id=\"form1\"]/form"));
        form.findElement(By.name("username")).sendKeys(Secret.vrainUser);
        form.findElement(By.name("password")).sendKeys(Secret.vrainPass);
        form.findElement(By.xpath("div/div[3]")).click();
        switchWindow(driver);

    }

    public void switchWindow(WebDriver driver) throws IOException, InterruptedException {
        Thread.sleep(10000);
        for (String handle : driver.getWindowHandles()) {

            driver.switchTo().window(handle);
            System.out.println("Handle windown: " + handle);
        }

        String tempfile = "D:/libs/" + System.currentTimeMillis() + ".html";
        Files.write(Paths.get(tempfile), driver.getPageSource().getBytes());
        log.write("temfile: " + tempfile);
        log.newLine();
        log.flush();


    }

    public void getTitleTime(WebDriver driver) throws IOException {
        //get time
        String sectionTitle = driver.findElement(By.cssSelector(".section.title")).getText().replace("Cập nhật lại", "");
        System.out.println(sectionTitle);
        log.write(sectionTitle);
        log.newLine();
        log.flush();
    }

    public void getListData(WebDriver driver) throws IOException {
        //get list elemt
        List<WebElement> listUiSegment = driver.findElement(By.cssSelector(".main-content.scrolling")).findElements(By.cssSelector(".ui.segment"));
        System.out.println("number list: " + listUiSegment.size());
        log.write("number list: " + listUiSegment.size());
        log.newLine();

        for (WebElement uiSegment : listUiSegment) {
            String label = uiSegment.findElement(By.cssSelector(".ui.basic.label.depth")).getText();
            System.out.println("label depth: " + label);
            log.write("label depth: " + label);
            log.newLine();

            String header = uiSegment.findElement(By.className("header")).getText().replace(label, "");
            System.out.print("header: " + header);
            log.write("header: " + header);
            log.newLine();

            String description = uiSegment.findElement(By.className("description")).getText();
            System.out.println("description: " + description);
            log.write("description: " + description);
            log.newLine();

            log.flush();
        }
    }

    public void closeDriver(WebDriver driver) throws IOException, InterruptedException {
        Thread.sleep(7000);
        driver.close();
        driver.quit();
        log.close();

        new WindowsProcessKiller().kill("chromedriver.exe");
    }
}
