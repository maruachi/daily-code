package _20220922;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream bis = new ByteArrayInputStream(new byte[0]);

        for (String filename : args) {
            bis = new SequenceInputStream(bis, new FileInputStream(filename));
        }

        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
        char[] buffer = new char[2];
        while (true) {
            int len = isr.read(buffer);
            if (len == -1) {
                break;
            }
            String s = new String(buffer, 0, len);
            System.out.print(s);
        }
    }
}
