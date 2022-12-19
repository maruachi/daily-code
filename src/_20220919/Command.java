package _20220919;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Command {
    public static void main(String[] args) throws IOException {
        while (true){
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

            String command = stringTokenizer.nextToken();
            List<String> filenames = new ArrayList<>();
            while(stringTokenizer.hasMoreTokens()){
                filenames.add(stringTokenizer.nextToken());
            }
//            System.out.println("command: " + command);
//            System.out.println("filenames: " + filenames);
            int countFilenames = filenames.size();

            if (command.equals("cp") && countFilenames == 2) {
                String sourceFilename = filenames.get(0);
                String targetFilename = filenames.get(1);
                commandCopy(sourceFilename, targetFilename);
                continue;
            }

            if(command.equals("mv") && countFilenames == 2){
                String sourceFilename = filenames.get(0);
                String targetFilename = filenames.get(1);
                commandMove(sourceFilename, targetFilename);
            }

            if(command.equals("cat") && countFilenames == 2){
                String sourceFilename1 = filenames.get(0);
                String sourceFilename2 = filenames.get(1);
                commandCat(sourceFilename1);
                commandCat(sourceFilename2);
            }

            if(command.equals("cat") && countFilenames == 1){
                String sourceFilename = filenames.get(0);
                commandCat(sourceFilename);
            }

            if(command.equals("quit") && countFilenames == 0){
                System.exit(100);
            }
        }
    }

    private static void commandCat(String sourceFilename) throws IOException {
        InputStream inputStream = new FileInputStream(sourceFilename);
        OutputStream outputStream = System.out;

        byte[] buffer = new byte[10];
        while(true){
            int len = inputStream.read(buffer);
            if (len == -1) {
                break;
            }
            outputStream.write(buffer, 0, len);
        }
        outputStream.write("\n".getBytes(StandardCharsets.UTF_8));

        inputStream.close();
    }

    private static void commandMove(String sourceFilename, String targetFilename) throws IOException {
        commandCopy(sourceFilename, targetFilename);
        new File(sourceFilename).delete();
    }

    private static void commandCopy(String sourceFilename, String targetFilename) throws IOException {
        InputStream inputStream = new FileInputStream(sourceFilename);
        OutputStream outputStream = new FileOutputStream(targetFilename);

        byte[] buffer = new byte[10];
        while(true){
            int len = inputStream.read(buffer);
            if (len == -1) {
                break;
            }
            outputStream.write(buffer, 0, len);
        }

        inputStream.close();
        outputStream.close();
    }
}
