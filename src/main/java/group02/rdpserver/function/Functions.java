package group02.rdpserver.function;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Functions {
    public static void shutDown(){
        try {
            Runtime.getRuntime().exec("shutdown /s");
        }
        catch (IOException err) {

        }
    }

    public static void takeScreenShot(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");

        Calendar now = Calendar.getInstance();
        Robot robot = null;
        try {
            robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(screenShot, "JPG", new File("d:\\"+formatter.format(now.getTime())+".jpg"));
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void getProcess(){
        try {
            String line;
            Process p = Runtime.getRuntime().exec("tasklist");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line); //<-- Parse data here.
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static void getApp(){
        try {
            String line;
            Process p = Runtime.getRuntime().exec("powershell \"gps | where {$_.MainWindowTitle } | select id, name");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(Arrays.toString(line.split("\s+"))); //<-- Parse data here.
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static void killProcess(String PID){
        try {
            Process p = Runtime.getRuntime().exec("taskkill /PID" + PID );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void killApp(String PID){
        try {
            Process p = Runtime.getRuntime().exec("taskkill /PID" + PID );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getKeyStroke(){

    }


    public static void startApp(String appName){
        try {
            Process p = Runtime.getRuntime().exec("taskkill /PID" + appName + ".exe" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startProccess(String processName){
        try {
            Process p = Runtime.getRuntime().exec("taskkill /PID" + processName + ".exe" );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
