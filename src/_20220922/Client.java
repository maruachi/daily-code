package _20220922;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.5", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("클라이언트 메세지 전송 완료".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(inputStreamReader, 20);

        while (true) {
            String line = br.readLine();
            System.out.println(line);
        }
    }
}
