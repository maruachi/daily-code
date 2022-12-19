package _20221119_home;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Log {
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    private final String filename;

    public Log(String filename) {
        this.filename = filename;
    }

    public void write(String log) {
        try (InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(log.getBytes(ENCODING)), 800);
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename, true), 800)) {
            byte[] buffer = new byte[400];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    break;
                }
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public boolean exist() {
        File logFile = new File(filename);
        return logFile.exists();
    }

    public boolean noExist() {
        return !exist();
    }

    public int countLine() {
        if (noExist()) {
            return 0;
        }

        int count = 0;
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filename), 4000);
             Reader reader = new InputStreamReader(inputStream, ENCODING)) {

            char[] buffer = new char[2000];
            while (true) {
                int len = reader.read(buffer);
                if (len == -1) {
                    break;
                }
                for (int i = 0; i < len; i++) {
                    if (buffer[i] == '\n') {
                        count++;
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return -1;
        }
        return count;
    }

    public void clear() {
        try (RandomAccessFile log = new RandomAccessFile(filename, "rw")) {
            log.setLength(0);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void copyTo(Log targetLog) {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filename), 800);
             OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(targetLog.filename, true), 800)) {
            byte[] buffer = new byte[400];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    break;
                }
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
