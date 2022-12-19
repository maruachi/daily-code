package _20221126_home;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        Socket socket = serverSocket.accept();

        try (OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream(), 8192);
             InputStream inputStream = new BufferedInputStream(socket.getInputStream(), 8192);
             BufferedReader reader = toReader(inputStream);) {
            String line = reader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
            String method = stringTokenizer.nextToken();
            String command = stringTokenizer.nextToken().substring("/".length());

            if ("read".equals(command)) {
//                파일 이름을 획득 -> http headeer를 출력 -> 파일 이름을 출력
                showAllFile(outputStream);
                return;
            }

//            process 대로 처리하기
            if ("GET".equals(method) && command.startsWith("read")) {
                String filename = command.substring("read/".length());

                showFileContent(outputStream, filename);
                return;
            }

            if ("POST".equals(method) && command.equals("write")) {
                String boundary = findBoundary(reader);

                uploadFile(reader, boundary);
                showAllFile(outputStream);
            }

//            error 처리 추가
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        socket.close();
    }

    private static void uploadFile(BufferedReader reader, String boundary) throws IOException {
        String filename = findFilename(reader, boundary);
        reader.readLine();
        reader.readLine();

        char[] buffer = new char[5000];

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename), 8192);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            while (true) {

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static String findFilename(BufferedReader reader, String boundary) throws IOException {
        String targetLine = null;

        while (reader.ready()) {
            String line = reader.readLine();
            if (line.startsWith("--") && line.substring("--".length()).equals(boundary)) {
                targetLine = reader.readLine();
                break;
            }
        }

        if (targetLine == null) {
            throw new RuntimeException();
        }

        String subLineWithName = targetLine.substring("Content-Disposition: form-data; name=\"".length());

        return subLineWithName.substring(0, subLineWithName.indexOf('\"'));
    }

    private static String findBoundary(BufferedReader reader) throws IOException {
        String targetLine = null;
        while (reader.ready()) {
            String line = reader.readLine();
            if (line.startsWith("Content-Type: ")) {
                targetLine = line;
                break;
            }
        }

        if (targetLine == null) {
            throw new RuntimeException();
        }

        return targetLine.substring("Content-Type: multipart/form-data; boundary=".length());
    }

    private static void showFileContent(OutputStream outputStream, String filename) {
        File targetFile = new File("/Users/dgyim/IdeaProjects/daily-code/Resources/" + filename);
        if (!targetFile.exists()) {
            sendNotFound(outputStream);
            return;
        }

        sendHttpHead(outputStream, "httpHeadResponseRead.head");
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(targetFile.getAbsolutePath()), 8192)) {
            byte[] buffer = new byte[5000];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void sendNotFound(OutputStream outputStream) {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream("/Users/dgyim/IdeaProjects/daily-code/src/_20221126_home/NotFoundResponse.head"), 8192)) {
            byte[] buffer = new byte[5000];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void showAllFile(OutputStream outputStream) {
        String[] filenames = new File("/Users/dgyim/IdeaProjects/daily-code/Resources").list();
        if (filenames == null) {
            return;
        }
        sendHttpHead(outputStream, "httpHeadResponseRead.head");

        try (BufferedWriter writer = toWriter(outputStream);) {
            writer.write("Resources");
            writer.newLine();
            writer.newLine();
            for (String filename : filenames) {
                writer.write(filename);
                writer.newLine();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void sendHttpHead(OutputStream outputStream, String filename) {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream("src/_20221126_home/" + filename), 8192)) {
            byte[] buffer = new byte[5000];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static BufferedWriter toWriter(OutputStream outputStream) {
        OutputStreamWriter osw = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        return new BufferedWriter(osw, 8192);
    }

    private static BufferedReader toReader(InputStream inputStream) {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        return new BufferedReader(reader, 8192);
    }

}
