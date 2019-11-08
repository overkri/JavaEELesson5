package httpServer;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 8000;
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("Connected");
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Type get and it will send you current directory files and directories.");
            System.out.println();

            while (true) {
                line = keyboard.readLine();
                System.out.println("Sending request to the server...");
                out.writeUTF(line);
                out.flush();
                line = in.readUTF();
                if (line.equals("404")) {
                    System.out.println("Wrong get request");
                    System.out.println("404 Error");
                    break;
                } else {
                    FilesList("C:\\Users\\user\\Desktop\\httpServer");

                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }

    }

    public static void FilesList(String path) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .forEach(System.out::println);
        Files.walk(Paths.get(path))
                .filter(Files::isDirectory)
                .forEach(System.out::println);



    }
}

