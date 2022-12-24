package _20221220_home;

import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader reader = toReader(socket.getInputStream());
                 OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream(), 8192);
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            ) {
                String beginLine = reader.readLine();
                StringTokenizer beginLineParser = new StringTokenizer(beginLine, " ");
                String method = beginLineParser.nextToken();
                String url = beginLineParser.nextToken();
                String version = beginLineParser.nextToken();

                if (method == null || url == null || version == null) {
                    continue;
                }

                HashMap<String, String> header = new HashMap<>();
                while (reader.ready()) {
                    String line = reader.readLine();
                    if ("".equals(line)) {
                        break;
                    }
                    StringTokenizer headerParser = new StringTokenizer(line, ":");
                    String keyHeader = headerParser.nextToken();
                    String valueHeader = headerParser.nextToken();
                    header.put(keyHeader, valueHeader);
                }

                String basePath = "/Users/dgyim/IdeaProjects/daily-code/Resources";
                if ("GET".equals(method) && url.startsWith("/index")) {
                    writer.write("http/1.1 200 OK\n");
                    writer.write('\n');

                    File resources = new File(basePath);
                    List<File> files = findAllFiles(resources);
                    for (File file : files) {
                        Path relativePath = resources.toPath().relativize(file.toPath());
                        writer.write(relativePath.toString());
                        writer.write('\n');
                    }
                    continue;
                }

                if ("GET".equals(method) && url.startsWith("/read")) {
                    String filename = url.substring("/read".length());
                    File file = new File(basePath + filename);
                    if (file.isFile()) {
                        writer.write("http/1.1 200 OK\n");
                        writer.write('\n');
                        writer.flush();

                        try (InputStream fis = new FileInputStream(file.getAbsolutePath());
                             InputStream inputStream = new BufferedInputStream(fis, 8192)) {
                            byte[] buffer = new byte[8192];
                            while (true) {
                                int len = inputStream.read(buffer);
                                if (len == -1) {
                                    break;
                                }
                                outputStream.write(buffer, 0, len);
                            }
                        } catch (IOException ioException) {

                        }
                        outputStream.flush();
                    }
                    continue;
                }

                if ("POST".equals(method) && url.startsWith("/upload")) {
                    String filename = url.substring("/read".length());
                    File file = new File(basePath + filename);
                    if (file.isFile()) {
//                        fail code
                        continue;
                    }

                    writer.write("http/1.1 200 OK\n");
                    writer.write('\n');
                    writer.flush();

                    try (OutputStream fos = new FileOutputStream(file.getAbsolutePath());
                         OutputStream uploadStream = new BufferedOutputStream(fos, 8192)) {
                        String line = reader.readLine();
                    } catch (IOException ioException) {
                        String contentTypeProperties = header.get("content-type");
                        StringTokenizer stringTokenizer = new StringTokenizer(contentTypeProperties, ";");
                        String type = stringTokenizer.nextToken();
                        if (!type.toLowerCase().equals("form-data")) {
//                            실패코드
                        }
                        String boundaryLine = stringTokenizer.nextToken();
                        int boundaryStartIndex = boundaryLine.indexOf('\"');
                        String boundarySubLine = boundaryLine.substring(boundaryStartIndex+1);
                        int boundaryEndIndex = boundarySubLine.indexOf('\"');
                        String boundary = boundaryLine.substring(0, boundaryEndIndex);

                        String startBoundary = "--" + boundary;
                        while (reader.ready()) {
                            String line = reader.readLine();
                            if (startBoundary.equals(line)) {
                                break;
                            }
                        }

//                        form-data 시작 부분까지 stream 커서를 이동
                        while (reader.ready()) {
                            String line = reader.readLine();
                            if ("".equals(line)) {
                                break;
                            }
                        }

                        writer.write("http/1.1 200 OK\n");
                        writer.write('\n');
                        writer.flush();

                        String endBoundary = startBoundary + "--";
                        while (reader.ready()) {
                            String line = reader.readLine();
                            if (endBoundary.equals(line)) {
                                break;
                            }
                            writer.write(line);
                            writer.write('\n');
                        }
                    }
                    outputStream.flush();

                    continue;
                }

//                Fail
                writer.write("http/1.1 404 Not Found\n");
                writer.write("\n");
                writer.write("Not found");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static String findCommand(String url) {
        if (url == null) {
            throw new RuntimeException();
        }
        String commandLine = url.substring("/".length());

        int indexCommand = commandLine.indexOf('/');
        if (indexCommand == -1) {
            return commandLine;
        }

        return commandLine.substring(0, indexCommand);
    }

    private static List<File> findAllFiles(File resources) {
        if (resources == null) {
            throw new RuntimeException();
        }

        if (!resources.isDirectory()) {
            throw new RuntimeException();
        }

        List<File> files = new ArrayList<>();
        Stack<File> dirs = new Stack<>();
        dirs.add(resources);
        while (!dirs.isEmpty()) {
            File dir = dirs.pop();
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    dirs.push(file);
                    continue;
                }
                files.add(file);
            }
        }

        return files;
    }

    private static BufferedReader toReader(InputStream inputStream) {
        InputStream bis = new BufferedInputStream(inputStream, 8192);
        InputStreamReader reader = new InputStreamReader(bis, StandardCharsets.UTF_8);

        return new BufferedReader(reader, 8192);
    }
}
