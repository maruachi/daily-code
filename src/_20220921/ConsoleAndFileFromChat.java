package _20220921;

import sun.awt.image.InputStreamImageSource;

import java.io.*;

public class ConsoleAndFileFromChat {
    public static void main(String[] args) throws IOException {
        InputStream sin = System.in;
        OutputStream[] outs = new OutputStream[]{
                new FileOutputStream("outfile"),
                System.out
        };

        byte[] buffer = new byte[10];
        do {
            int len = sin.read(buffer);
            writeByAllOutStream(outs, buffer, len);
        }while (sin.available() != 0);
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
