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
    public static BufferedReader getProcess(){
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("tasklist");
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        } catch (Exception err) {
            err.printStackTrace();
        }
        return input;
    }


    public static BufferedReader getApp(){
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("powershell \"gps | where {$_.MainWindowTitle } | select id, name");
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));

        } catch (Exception err) {
            err.printStackTrace();
        }
        return input;

    }

    public static void startApp(String appName){
        try {
            String cmd = "cmd.exe /c" + "start " + appName + ".exe";
            Process p = Runtime.getRuntime().exec( cmd);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startProccess(String processName){
        try {
            String cmd = "cmd.exe /c" + "start " + processName + ".exe";
            Process p = Runtime.getRuntime().exec( cmd);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static boolean killProcess(String PID){
        try {
            Process p = Runtime.getRuntime().exec("powershell " +" taskkill /PID " + PID );
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            if(input.readLine() == null){
                return false;
            }
            input.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


    public static boolean killApp(String PID){
        try {
            Process p = Runtime.getRuntime().exec("powershell " +" taskkill /PID " + PID );
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            if(input.readLine() == null){
                return false;
            }
            input.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public static void shutDown(){
        try {
            Runtime.getRuntime().exec("shutdown /s");
        }
        catch (IOException err) {

        }
    }

    public static void takeScreenShot(){
        try {
            BufferedImage screenShot =  new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(screenShot, "JPG", new File("D:\\screenshot.jpg"));
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }

    }







    public static void getKeyStroke(){

    }



}
