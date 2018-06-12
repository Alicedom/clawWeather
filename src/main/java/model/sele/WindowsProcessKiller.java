package model.sele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsProcessKiller {

    public void kill(String processName) {
//        String processName = "chromedriver.exe";
        while (true) {
            boolean isRunning = isProcessRunning(processName);

            System.out.println("is " + processName + " running : " + isRunning);

            if (isRunning) {
                killProcess(processName);
            } else {
                System.out.println("Not able to find the process : " + processName);
                break;
            }

        }
    }

    public boolean isProcessRunning(String serviceName) {

        try {
            Process pro = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                if (line.startsWith(serviceName)) {
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void killProcess(String serviceName) {

        try {
            Runtime.getRuntime().exec("taskkill /IM " + serviceName + " /F");
            System.out.println(serviceName + " killed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}