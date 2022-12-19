package _20221208;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket = createServerSocket(7777);

        try (Socket socket = serverSocket.accept();
             BufferedReader reader = toReader(socket.getInputStream());
             OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream(), 8192);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), 8192)) {
            String line = reader.readLine();

            StringTokenizer stringTokenizer = new StringTokenizer(line," ");
            String method = stringTokenizer.nextToken();
            String url = stringTokenizer.nextToken();
            String version = stringTokenizer.nextToken();

            if ("/index".equals(url)) {
                writer.write("Http/1.1 200 OK");
                writer.write('\n');
                writer.write('\n');
                writer.write("hello world!");
                writer.flush();
                return;
            }

            String filename = url.substring("/".length());
            writer.write("Http/1.1 200 OK");
            writer.write('\n');
            writer.write('\n');
            writer.flush();

            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename), 8192);) {
                byte[] buffer = new byte[1000];
                while (true) {
                    int len = inputStream.read(buffer);
                    if (len == -1) {
                        outputStream.flush();
                        break;
                    }
                    outputStream.write(buffer, 0, len);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static ServerSocket createServerSocket(int port) {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            return serverSocket;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedReader toReader(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);

        return new BufferedReader(inputStreamReader, 8192);
    }
}
