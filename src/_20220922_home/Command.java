package _20220922_home;

import com.apple.laf.AquaMenuItemUI;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Command {
    public static void main(String[] args) throws IOException {

        Set<String> commands = new HashSet<>();
        commands.add("mv");
        commands.add("cp");

        while (true) {
            InputStream sin = System.in;
            BufferedInputStream bis = new BufferedInputStream(sin, 8196);
            InputStreamReader isr = new InputStreamReader(bis);
            BufferedReader br = new BufferedReader(isr, 8196);

            String line = br.readLine();
            if (line.equals("quit")) {
                return;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }

            String command = stringTokenizer.nextToken();
            if (isNotCommand(command)) {
                return;
            }


            int countFilenames = stringTokenizer.countTokens();
            if (commands.contains(command) && countFilenames != 2) {
                return;
            }

            if ("cat".equals(command) && countFilenames == 0) {
                return;
            }

            if ("mv".equals(command)) {
                String sourceFilename = stringTokenizer.nextToken();
                String targetFilename = stringTokenizer.nextToken();
                runCommandMove(sourceFilename, targetFilename);

                continue;
            }

            if ("cp".equals(command)) {
                String sourceFilename = stringTokenizer.nextToken();
                String targetFilename = stringTokenizer.nextToken();
                runCommandCopy(sourceFilename, targetFilename);

                continue;
            }

            if ("cat".equals(command)) {
                String[] sourceFilenames = new String[countFilenames];
                for (int i = 0; i < countFilenames; i++) {
                    sourceFilenames[i] = stringTokenizer.nextToken();
                }

                runCommandCat(sourceFilenames);
            }
        }
    }

    private static void runCommandCat(String[] sourceFilenames) throws IOException {
        InputStream sis = new SequenceInputStream(new ByteArrayInputStream(new byte[0]), new FileInputStream(sourceFilenames[0]));
        for (int i = 1; i < sourceFilenames.length; i++) {
            sis = new SequenceInputStream(sis, new FileInputStream(sourceFilenames[i]));
        }


        InputStream bis = new BufferedInputStream(sis, 8196);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr, 8196);

        OutputStream sout = System.out;
        OutputStream bos = new BufferedOutputStream(sout, 8196);
        OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            osw.write(line);
            osw.write("\n");
        }

        br.close();
        osw.flush();
        osw.close();
    }

    private static void runCommandCopy(String sourceFilename, String targetFilename) throws IOException {
        InputStream fis = new FileInputStream(sourceFilename);
        InputStream bis = new BufferedInputStream(fis, 8196);
        OutputStream fos = new FileOutputStream(targetFilename);
        OutputStream bos = new BufferedOutputStream(fos, 8196);

        byte[] buffer = new byte[100];
        while (true) {
            int len = bis.read(buffer);
            if (len == -1) {
                break;
            }
            bos.write(buffer, 0, len);
        }

        bis.close();
        bos.flush();
        bos.close();
    }

    private static void runCommandMove(String sourceFilename, String targetFilename) throws IOException {
        runCommandCopy(sourceFilename, targetFilename);

        new File(sourceFilename).delete();
    }

    private static boolean isNotCommand(String command) {
        return !isCommand(command);
    }

    private static boolean isCommand(String command) {
        return "mv".equals(command) || "cp".equals(command) || "cat".equals(command);
    }
}
