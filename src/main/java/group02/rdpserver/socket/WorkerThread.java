package group02.rdpserver.socket;


import java.io.*;
import java.net.Socket;

import static group02.rdpserver.function.Functions.*;

public class WorkerThread extends Thread{
    private Socket socket;

    public static DataInputStream dis = null;
    public static DataOutputStream dos = null;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("Processing: " + socket);
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            while (!socket.isClosed()) {
                String flag = dis.readUTF();

                switch (flag){
                    case "getProcess":
                        getProcess();
                        break;

                    case "getApp":
                        getApp();
                        break;

                    case "killProcess":
                        killProcess("");
                        break;

                    case "killApp":
                        killApp("");
                        break;

                    case "getKeyStroke":

                        break;

                    case "takeScreenShot":
                        takeScreenShot();
                        break;

                    case "shutDown":
                        shutDown();
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
