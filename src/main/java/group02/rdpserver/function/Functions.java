package group02.rdpserver.function;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;


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

    public static boolean startApp(String appName){

        try {
            String cmd = "powershell " + "start " + appName + ".exe";
            Process p = Runtime.getRuntime().exec(cmd);

            BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            //System.out.println(input.readLine());
            if(input.readLine() != null){
                return false;
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean startProccess(String processName){
        try {
            String cmd = "powershell " + "start " + processName + ".exe";
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            if(input.readLine() != null){
                return false;
            }
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

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

    public static GlobalKeyboardHook getKeyStrokeOn(ArrayList<Integer> listKeypress) {
        try {
            GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);

            keyboardHook.addKeyListener(new GlobalKeyAdapter() {
                @Override
                public void keyPressed(GlobalKeyEvent event) {
                    //System.out.println(new Date() + " - " + event.getKeyChar() + " - " + event);
                    listKeypress.add(event.getVirtualKeyCode());
                }
            });

            return keyboardHook;
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();

            return null;
        }
    }

    public static boolean getKeyStrokeOff(GlobalKeyboardHook keyboardHook) {
        try {
            keyboardHook.shutdownHook();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
