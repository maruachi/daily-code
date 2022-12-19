package _20221119_home;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main3 {
    public static void main(String[] args) {
        writeMainLog("1\n");
        writeMainLog("2\n");
        writeMainLog("3\n");
        writeMainLog("4\n");
        writeMainLog("5\n");
        writeMainLog("6\n");
        writeMainLog("7\n");
        writeMainLog("8\n");
    }

    private static void writeMainLog(String logLine) {
        int MAX_LOG_LINE = 2;
        int MAX_INDEX = 100;
        Log mainLog = new Log("main.log");

        if (mainLog.countLine() >= MAX_LOG_LINE) {
            return;
        }

        mainLog.write(logLine);

        Log newLog = findNewLog(MAX_LOG_LINE, MAX_INDEX);

        if (newLog == null) {
            throw new RuntimeException("There are no space to store log");
        }

        if (mainLog.countLine() == MAX_LOG_LINE) {
            mainLog.copyTo(newLog);
            mainLog.clear();
        }
    }

    private static Log findNewLog(int MAX_LOG_LINE, int MAX_INDEX) {
        Log newLog = null;
        StringBuilder stringBuilder = new StringBuilder(100);
        for (int i = 0; i < MAX_INDEX; i++) {
            newLog = new Log(stringBuilder.append("main").append(i).append(".log").toString());
            if(newLog.noExist()){
                break;
            }

            if (newLog.countLine() < MAX_LOG_LINE) {
                break;
            }
            stringBuilder.setLength(0);
        }
        return newLog;
    }
}
