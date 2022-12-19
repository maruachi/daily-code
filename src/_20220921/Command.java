package _20220921;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.StringTokenizer;

public class Command {
    public static void main(String[] args) throws IOException {
        InputStream sin = System.in;
        InputStreamReader isr = new InputStreamReader(System.in);

        char[] cbuf = new char[10];
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int len = isr.read(cbuf);
            if (len == -1) {
                break;
            }


            stringBuilder.append(cbuf, 0, len);
        }
        String line = stringBuilder.toString();

        StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
        if (!stringTokenizer.hasMoreTokens()) {
            return;
        }

        String command = stringTokenizer.nextToken();
        if (isNotCommand(command)) {
            return;
        }
//        argument 개수가 맞는지 체크
//        set 자료 구조
        int countFilenames = stringTokenizer.countTokens();
//        while (stringTokenizer.hasMoreTokens()) {
//            String filename = stringTokenizer.nextToken();
//            runCommandCat(filename);
//        }
//        if ("mv".equals(command) && countFilenames != 2) {
//            return;
//        }
//
//        if ("cp".equals(command) && countFilenames != 2) {
//            return;
//        }
//
//        if ("cat".equals(command) && countFilenames != 2) {
//            return;
//        }

        if ("mv".equals(command) && countFilenames == 2) {

        }

        if ("cp".equals(command) && countFilenames == 2) {

        }

        if ("cat".equals(command) && countFilenames <= 2) {

        }
    }

    private static boolean isNotCommand(String commandName) {
        return !isCommand(commandName);
    }

    private static boolean isCommand(String commandName) {
        String[] commands = new String[]{
                "mv", "cp", "cat"
        };
        for (String command : commands) {
            if (command.equals(commandName)) {
                return true;
            }
        }
        return false;
    }
}
