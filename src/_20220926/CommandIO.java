package _20220926;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CommandIO {
    public static void main(String[] args) throws IOException {
        Set<String> commandChecker = new HashSet<>();
        commandChecker.add("mv");
        commandChecker.add("cp");
        commandChecker.add("cat");

        Set<String> binaryCommandChecker = new HashSet<>();
        binaryCommandChecker.add("mv");
        binaryCommandChecker.add("cp");

        while (true) {
            InputStream sin = System.in;
            BufferedInputStream bis = new BufferedInputStream(sin, 8196);
            InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr, 500);

            String line = br.readLine();

            if ("q".equalsIgnoreCase(line)) {
                return;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }

            String command = stringTokenizer.nextToken();
            if (!commandChecker.contains(command)) {
                return;
            }

            int countFilenames = stringTokenizer.countTokens();
            if (binaryCommandChecker.contains(command) && countFilenames != 2) {
                return;
            }

            List<String> filenames = new ArrayList<>();
            while (stringTokenizer.hasMoreTokens()) {
                filenames.add(stringTokenizer.nextToken());
            }

//            if ("mv".equals(command)) {
//                runMove(filenames.get(0), filenames.get(1));
//            }
//
//            if ("cp".equals(command)) {
//                runCopy(filenames.get(0), filenames.get(1));
//            }
//
//            if ("cat".equals(command)) {
//                runCat(filenames);
//            }

            Command.run(command, filenames);
        }
    }

//    private static void runCat(List<String> filenames) throws IOException {
//        InputStream sis = new SequenceInputStream(new ByteArrayInputStream(new byte[0]), new FileInputStream(filenames.get(0)));
//        for (int i = 1; i < filenames.size(); i++) {
//            sis = new SequenceInputStream(sis, new FileInputStream(filenames.get(i)));
//        }
//        BufferedInputStream bis = new BufferedInputStream(sis, 8196);
//        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
//        BufferedReader br = new BufferedReader(isr, 8196);
//        OutputStream sout = System.out;
//        BufferedOutputStream bos = new BufferedOutputStream(sout, 8196);
//        OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
//
//        while (true) {
//            String line = br.readLine();
//            if (line == null) {
//                break;
//            }
//            osw.write(line);
//            osw.write("\n");
//        }
//
//        br.close();
//        osw.flush();
//        osw.close();
//    }
//
//    private static void runCopy(String sourceFilename, String targetFilename) throws IOException {
//        InputStream fis = new FileInputStream(sourceFilename);
//        BufferedInputStream bis = new BufferedInputStream(fis, 8196);
//        OutputStream fos = new FileOutputStream(targetFilename);
//        BufferedOutputStream bos = new BufferedOutputStream(fos, 8196);
//
//        byte[] buffer = new byte[8196];
//        while (true) {
//            int len = bis.read(buffer);
//            if (len == -1) {
//                break;
//            }
//            bos.write(buffer, 0, len);
//        }
//
//        bis.close();
//        bos.flush();
//        bos.close();
//    }
//
//    private static void runMove(String sourceFilename, String targetFilename) throws IOException {
//        runCopy(sourceFilename, targetFilename);
//        new File(sourceFilename);
//    }
}

class Command {
    private static final Map<String, Object> values = new HashMap<>();

    static {
        values.put("cp", new Copy());
        values.put("mv", new Move());
        values.put("cat", new Cat());
    }

    public static void run(String command, List<String> filenames) throws IOException {
        if (!values.containsKey(command)) {
            throw new RuntimeException();
        }

        ((Commandable)values.get(command)).runCommand(filenames);
    }

    private static class Copy implements Commandable {

        @Override
        public void runCommand(List<String> filenaems) throws IOException {
            if (filenaems.size() != 2) {
                throw new RuntimeException();
            }
            String sourceFilename = filenaems.get(0);
            String targetFilename = filenaems.get(1);
            InputStream fis = new FileInputStream(sourceFilename);
            BufferedInputStream bis = new BufferedInputStream(fis, 8196);
            OutputStream fos = new FileOutputStream(targetFilename);
            BufferedOutputStream bos = new BufferedOutputStream(fos, 8196);

            byte[] buffer = new byte[8196];
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
    }

    private static class Move implements Commandable {
        @Override
        public void runCommand(List<String> filenaems) throws IOException {
            Command.run("cp", filenaems);
            new File(filenaems.get(0)).delete();
        }
    }

    private static class Cat implements Commandable {
        @Override
        public void runCommand(List<String> filenames) throws IOException {
            InputStream sis = new SequenceInputStream(new ByteArrayInputStream(new byte[0]), new FileInputStream(filenames.get(0)));
            for (int i = 1; i < filenames.size(); i++) {
                sis = new SequenceInputStream(sis, new FileInputStream(filenames.get(i)));
            }
            BufferedInputStream bis = new BufferedInputStream(sis, 8196);
            InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr, 8196);
            OutputStream sout = System.out;
            BufferedOutputStream bos = new BufferedOutputStream(sout, 8196);
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
    }

}