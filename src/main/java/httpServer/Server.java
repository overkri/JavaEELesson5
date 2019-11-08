package httpServer;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] ar) {
        int port = 8000;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");
            Socket socket = ss.accept();
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while (true) {
                line = in.readUTF();
                if (line.equals("get")) {
                    out.writeUTF("200");
                    out.flush();
                    System.out.println("I've recieved get request");
                    System.out.println("Waiting for another get request");

                } else {
                    out.writeUTF("404");
                    out.flush();
                    System.out.println("Wrong get request");
                    System.out.println("404 Error");
                    break;
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}