package _20220916;

import java.io.*;

public class ConsoleAndFileFromChat {
    public static void main(String[] args) throws IOException {
        InputStream sin = System.in;
        OutputStream sout = System.out;
        OutputStream fos = new FileOutputStream("fromChat.txt");

        int bufferSize = 10;
        byte[] buffer = new byte[bufferSize];

        while (true){
            int len = sin.read(buffer);
            if (len == -1) {
                break;
            }
            sout.write(buffer, 0, len);
            fos.write(buffer, 0, len);
        }
    }
}
