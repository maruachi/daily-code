package _20221117;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main3 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(7777);

        try (Socket socket = serverSocket.accept();
             InputStream inputStream = socket.getInputStream();
             InputStream bis = new BufferedInputStream(inputStream, 8192);
             InputStream fos = new BufferedInputStream(new FileInputStream("src/_20221117/index.html"), 8192);
             OutputStream outputStream = socket.getOutputStream();
             OutputStream bos = new BufferedOutputStream(outputStream, 512);
             Writer writer = new OutputStreamWriter(bos, StandardCharsets.UTF_8)){
            byte[] buffer = new byte[10];
            StringBuilder stringBuilder = new StringBuilder(256);
            while (true) {
                int len = bis.read(buffer);
                if (bis.available() == 0) {
                    break;
                }
                stringBuilder.append(new String(buffer, 0, len, StandardCharsets.UTF_8));
            }
            System.out.println(stringBuilder);
            writer.write("HTTP/1.1 200 OK\n");
            writer.write("Content-Type: text/html\n");
            writer.write("\n");

            String path = parsePath(stringBuilder);
            String name = path.substring(1);

            BufferedReader br = new BufferedReader(new InputStreamReader(fos, StandardCharsets.UTF_8), 8192);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String trimLine = line.trim();
                boolean isTemplateLine = trimLine.startsWith("{{");
                if (isTemplateLine && trimLine.contains("name")) {
                    writer.write(line.replace("name", name).replace("{{","").replace("}}","") + "\n");
                    continue;
                }
                writer.write(line + "\n");
            }
            writer.flush();

//            byte[] buf = new byte[100];
//            while (true) {
//                int len = fos.read(buf);
//                if (len == -1) {
//                    break;
//                }
//                bos.write(buf, 0, len);
//            }
//            bos.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static String parsePath(StringBuilder stringBuilder) {
        int beginIndex = stringBuilder.indexOf(" ");
        int splitLetterLen = " ".length();
        int endIndex = stringBuilder.indexOf(" ", beginIndex + splitLetterLen);

        return stringBuilder.substring(beginIndex + splitLetterLen, endIndex);
    }
}
