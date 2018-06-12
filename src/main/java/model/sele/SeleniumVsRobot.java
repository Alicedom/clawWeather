package model.sele;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class SeleniumVsRobot {
    Robot robot;

    public SeleniumVsRobot() {
        String exePath = "src/main/sources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
    }
    public static void main(String[] args) {
        new SeleniumVsRobot();
    }



    public static void typeCharacter(Robot robot, String letter) {
        try {
            boolean upperCase = Character.isUpperCase(letter.charAt(0));
            String variableName = "VK_" + letter.toUpperCase();

            Class clazz = KeyEvent.class;
            Field field = clazz.getField(variableName);
            int keyCode = field.getInt(null);

            robot.delay(1000);

            if (upperCase) robot.keyPress(KeyEvent.VK_SHIFT);

            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);

            if (upperCase) robot.keyRelease(KeyEvent.VK_SHIFT);
        } catch (Exception e) {
            System.out.println("Auto press error: " + e);
        }
    }


    public void login() throws AWTException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://ra2-nwp.kishou.go.jp/cityfc/VietNam/VietNam.html");

        robot = new Robot();
        new Runnable() {
            @Override
            public void run() {
                typeString(robot, Secret.ra2User);
            }
        }.run();

    }

    private void typeString(Robot robot, String letter) {
        char[] chars = letter.toCharArray();
        for (char chr : chars) {
            typeCharacter(robot, String.valueOf(chr));
            System.out.println(chr);
            System.out.println("chr = " + chr);


        }

    }
}
