package _20220922;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("connect the client".getBytes(StandardCharsets.UTF_8));

        InputStream inputStream = socket.getInputStream();
        InputStream bis = new BufferedInputStream(inputStream, 8192);
        InputStreamReader inputStreamReader = new InputStreamReader(bis, StandardCharsets.UTF_8);

        char[] cbuf = new char[10];
        while (true) {
            int len = inputStreamReader.read(cbuf);
            String s = new String(cbuf, 0, len);
            System.out.print(s);
        }
    }
}
