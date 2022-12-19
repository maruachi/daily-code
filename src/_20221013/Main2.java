package _20221013;

import javax.xml.stream.FactoryConfigurationError;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;


public class Main2 {
    public static void main(String[] args) throws IOException {
        InputStream sin = new ByteArrayInputStream("12\n35791111111111111111\n4567\n89".getBytes(StandardCharsets.UTF_8));
        BufferedInputStream bis = new BufferedInputStream(sin, 8192);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);

        char[] cbuf = new char[2];
        StringBuilder pool = new StringBuilder();
        boolean isTrash = false;
        while (true) {
            int len = isr.read(cbuf);
            if (len == -1) {
                System.out.println(pool);
                break;
            }
            int newLineIndex = findNewLineIndex(cbuf, len);
            boolean hasNotNewLine = (newLineIndex == -1);

            int newLineStart = newLineIndex + 1;
            int newLineLen = len - newLineStart;
            if (!hasNotNewLine) {
                if (!isTrash) {
                    pool.append(cbuf, 0, newLineIndex);
                    System.out.println(pool);
                }

                pool.setLength(0);
                isTrash = false;
            }

            if (hasNotNewLine && isTrash) {
                continue;
            }

            int poolDataLen = hasNotNewLine ? len : len - newLineStart;
            if (pool.length() + poolDataLen <= 3) {
                pool.append(cbuf, newLineStart, poolDataLen);
                continue;
            }

            isTrash = true;
            pool.setLength(0);
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

    private static boolean hasNotNewLine(char[] cbuf, int len) {
        for (int i = 0; i < len; i++) {
            if (cbuf[i] == '\n') {
                return false;
            }
        }
        return true;
    }
}
