package _20221119_home;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main2 {
    private static final int MAX_LOG_LINE_LIMIT = 2;
    private static final int MAX_LOG_INDEX_LIMIT = 100;
    private static final String mainLogFileName = "main.log";
    private static int targetLogFileIndex = 0;
    private static int numLineMainLog = 0;

    public static void main(String[] args) {
        numLineMainLog = countLogLines(mainLogFileName);
        writeMainLog("1");
        writeMainLog("2");
        writeMainLog("3");
        writeMainLog("4");
        writeMainLog("5");
        writeMainLog("6");
        writeMainLog("7");
        writeMainLog("8");
        writeMainLog("9");
        writeMainLog("10");
        writeMainLog("11");
    }

    private static void writeMainLog(String log) {
        updateTargetLogFileIndex();
        if (targetLogFileIndex == -1) {
            return;
        }

        writeLog(log, mainLogFileName);
        int numLine = countLogLines(mainLogFileName);

        if (numLine == MAX_LOG_LINE_LIMIT) {
            moveLog(mainLogFileName, createTargetLogFileName(targetLogFileIndex));
            targetLogFileIndex++;
        }
    }

    private static String createTargetLogFileName(int logFileIndex) {
        return "main" + logFileIndex + ".log";
    }

    private static void updateTargetLogFileIndex() {
        for (int logFileIndex = targetLogFileIndex; logFileIndex < MAX_LOG_INDEX_LIMIT; logFileIndex++) {
            String targetLogFileName = createTargetLogFileName(logFileIndex);

            File file = new File(targetLogFileName);
            if (!file.exists() && countLogLines(targetLogFileName) < MAX_LOG_LINE_LIMIT) {
                targetLogFileIndex = logFileIndex;
                break;
            }
        }
    }

    private static void moveLog(String soureLogFileName, String targetLogFileName) {
        try (InputStream source = new BufferedInputStream(new FileInputStream(soureLogFileName), 8192);
             OutputStream target = new BufferedOutputStream(new FileOutputStream(targetLogFileName, true), 8192);) {
            byte[] buffer = new byte[100];
            while (true) {
                int len = source.read(buffer);
                if (len == -1) {
                    break;
                }
                target.write(buffer, 0, len);
            }
            target.flush();
        } catch (IOException iOException) {

        }

        try (RandomAccessFile mainLogFile = new RandomAccessFile("main.log", "rw")) {
            mainLogFile.setLength(0);
            numLineMainLog = 0;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static int countLogLines(String logName) {
        int numLine = 0;
        try (InputStream sourceLog = new BufferedInputStream(new FileInputStream("main.log"), 8192);
             Reader sourceLogReader = new BufferedReader(new InputStreamReader(sourceLog, StandardCharsets.UTF_8))) {
            char[] cbuf = new char[100];

            while (true) {
                int len = sourceLogReader.read(cbuf);
                if (len == -1) {
                    break;
                }

                for (int i = 0; i < len; i++) {
                    if (cbuf[i] == '\n') {
                        numLine++;
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            return 0;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return -1;
        }

        return numLine;
    }

    public static void writeLog(String log, String logFileName) {
        if (numLineMainLog < MAX_LOG_LINE_LIMIT) {
            try (OutputStream targetLog = new BufferedOutputStream(new FileOutputStream(logFileName, true), 8192);) {
                targetLog.write(log.getBytes(StandardCharsets.UTF_8));
                targetLog.write("\n".getBytes(StandardCharsets.UTF_8));
                targetLog.flush();
                numLineMainLog++;
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return;
            }
        }
    }
}
