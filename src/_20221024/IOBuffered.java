package _20221024;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOBuffered {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("tmp.txt");
        InputStream bis = new BufferedInputStream(inputStream, 8192);
        byte[] buffer = new byte[10];
        int len = bis.read(buffer);
        StringBuilder stringBuilder = new StringBuilder();
        String s = new String(buffer, 0, len, StandardCharsets.UTF_8);
        stringBuilder.append(s);
        System.out.println(stringBuilder);
    }
}