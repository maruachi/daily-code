package _20220922_home;

import java.io.*;

public class ConsoleAndFileFromChat {
    public static void main(String[] args) throws IOException {
        OutputStream[] outs = new OutputStream[]{
                new FileOutputStream("outfile"),
                System.out
        };

        InputStream sin = System.in;
        InputStream bis = new BufferedInputStream(sin, 8196);
        byte[] buffer = new byte[10];
        while (true) {
            int len = bis.read(buffer);
            if (len == -1) {
                break;
            }
            writeByAllOutStream(outs, buffer, len);
        }
        sin.close();

        closeAllOutStream(outs);
    }

    private static void closeAllOutStream(OutputStream[] outs) throws IOException {
        for(OutputStream out : outs){
            out.flush();
            out.close();
        }
    }

    private static void writeByAllOutStream(OutputStream[] outs, byte[] buffer, int len) throws IOException {
        for (OutputStream out : outs) {
            out.write(buffer, 0, len);
        }
    }

}
