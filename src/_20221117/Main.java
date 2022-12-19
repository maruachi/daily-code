package _20221117;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final Charset MY_ENCODING = Charset.forName("utf-8");

    public static void main(String[] args) {
        String string = "한글hello world";
        byte[] bytes = string.getBytes(MY_ENCODING);

        StringBuilder stringBuilder = new StringBuilder(100);
        try (InputStream inputStream = new ByteArrayInputStream(bytes);
            InputStream bis = new BufferedInputStream(inputStream, 8192);
//            Reader isr = new InputStreamReader(bis, MY_ENCODING);
            OutputStream outputStream = System.out;
            OutputStream bos = new BufferedOutputStream(outputStream, 2);
//            Writer osw = new OutputStreamWriter(bos, MY_ENCODING)
            ){
//            char[] cbuffer = new char[2];
            byte[] buffer = new byte[2];
            while (true) {
                int len = bis.read(buffer);
                if (len == -1) {
//                    System.out.println(stringBuilder);
                    break;
                }
                bos.write(buffer, 0, len);
//                stringBuilder.append(cbuffer, 0, len);
            }
            bos.flush();
//
//            byte[] buffer = new byte[3];
//            while (true) {
//                int len = bis.read(buffer);
//                if (len == -1) {
//                    System.out.println(stringBuilder);
//                    break;
//                }
//                String bufferToString = new String(buffer, 0, len, Charset.forName("EUC-KR"));
//                stringBuilder.append(bufferToString);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
