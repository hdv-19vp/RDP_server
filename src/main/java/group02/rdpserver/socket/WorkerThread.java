package group02.rdpserver.socket;


import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Arrays;

import static group02.rdpserver.function.Functions.*;

public class WorkerThread extends Thread{
    private Socket socket;

    public static DataInputStream dis = null;
    public static DataOutputStream dos = null;
    public static BufferedReader input = null;
    public static String line;
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
                        input = getProcess();

                        while ((line = input.readLine()) != null) {
                            dos.writeUTF("start");
                            dos.writeUTF(line);
                        }
                        dos.writeUTF("end");
                        dos.writeUTF("success");
                        input.close();
                        break;

                    case "getApp":
                        input = getApp();

                        while ((line = input.readLine()) != null) {
                            dos.writeUTF("start");
                            dos.writeUTF(line);
                        }
                        dos.writeUTF("end");
                        dos.writeUTF("success");
                        input.close();
                        break;

                    case "startApp" :
                        startApp(dis.readUTF());

                        dos.writeUTF("success");
                        break;

                    case "startProcess":
                        startProccess(dis.readUTF());

                        dos.writeUTF("success");
                        break;

                    case "killProcess":
                        if(killProcess(dis.readUTF())){
                            dos.writeUTF("success");
                            break;
                        }
                        dos.writeUTF("error");
                        break;

                    case "killApp":
                        if(killApp(dis.readUTF())){
                            dos.writeUTF("success");
                            break;
                        }
                        dos.writeUTF("error");
                        break;

                    case "getKeyStroke":

                        break;

                    case "takeScreenShot":
                        takeScreenShot();
                        File file = new File("D:\\screenshot.jpg");
                        byte[]  bytes = Files.readAllBytes(file.toPath());
                        file.deleteOnExit();
                        dos.writeInt(bytes.length);
                        dos.write(bytes);

                        break;

                    case "shutDown":
                        shutDown();
                        dos.writeUTF("success");
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
