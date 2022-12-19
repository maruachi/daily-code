package _20221124;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader toReader(InputStream inputStream) {
        InputStream inputStream1 = new BufferedInputStream(inputStream, 8192);
        Reader reader = new InputStreamReader(inputStream1, StandardCharsets.UTF_8);

        return new BufferedReader(reader, 8192);
    }

    public static void main(String[] args) {
        try (BufferedReader reader = toReader(System.in)) {
            while (true) {
                doFileSession(reader);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void doFileSession(BufferedReader reader) throws IOException {
        String filename = reader.readLine();

        if (filename == null) {
            return;
        }

        try (Writer writer = toAppendWriter(filename)) {
            openFileSession(reader, writer);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void openFileSession(BufferedReader reader, Writer writer) throws IOException {
        while (true) {
            String message = reader.readLine();

            if ("exit".equals(message)) {
                writer.flush();
                return;
            }

            if (message.startsWith("echo:")) {
                String filename = message.substring("echo:".length()).trim();

                writer.flush();
                printFileContent(filename);
                continue;
            }

            writer.write(message);
            writer.write("\n");
        }
    }

    private static void printFileContent(String filename) {
        OutputStream outputStream = new BufferedOutputStream(System.out, 8192);
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filename), 8192)) {
            byte[] buffer = new byte[100];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(buffer,0,len);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //    일반적으로 에외 처리는 내부에서 처리하는 게 좋다.
//    하지만 이 경우에는 내부적으로 하는 게 좋다 왜냐하면 null처리가 추가되기 때문이다.
    private static Writer toAppendWriter(String filename) throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream(filename);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        Writer writer = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);

        return writer;
    }
}
