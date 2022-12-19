package _20221013;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("hello world");

        HashMap<String, String> mappingTable = new HashMap<>();
        mappingTable.put("abcd", "123");
        mappingTable.put("한글", "english");

//        InputStream sin = System.in;
        InputStream sin = new ByteArrayInputStream("123\n456789".getBytes(StandardCharsets.UTF_8));
        BufferedInputStream bis = new BufferedInputStream(sin, 8192);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);

        char[] cbuf = new char[5];
        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            int len = isr.read(cbuf);
            if (len == -1) {
                System.out.println(stringBuilder);
                return;
            }

            if (!hasNewLine(cbuf, len)) {
                stringBuilder.append(cbuf, 0, len);
                continue;
            }

            int newLineIndex = findNewLineIndex(cbuf, len);

            stringBuilder.append(cbuf, 0, newLineIndex);

            System.out.println(stringBuilder);

            stringBuilder.setLength(0);
            int newLineStart = newLineIndex + 1;
            stringBuilder.append(cbuf, newLineStart, len - newLineStart);
        }
    }

    private static int findNewLineIndex(char[] cbuf, int len) {
        for (int i = 0; i < len; i++) {
            if (cbuf[i] == '\n') {
                return i;
            }
        }
        return -1;
    }

    private static boolean hasNewLine(char[] cbuf, int len) {
        for (int i = 0; i < len; i++) {
            if (cbuf[i] == '\n') {
                return true;
            }
        }
        return false;
    }
}
