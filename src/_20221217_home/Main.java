package _20221217_home;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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

                if ("GET".equals(method)) {
//                    성공에 대한 header를 보낸다.
//                    Path basePath = Paths.get("/Users/dgyim/IdeaProjects/daily-code/Resources");
//                    beginline
                    writer.write("http/1.1 200 OK\n");
//                    header
                    writer.write("\n");
                    writer.flush();

//                    body를 분기 처리
                    String basePath = "/Users/dgyim/IdeaProjects/daily-code/Resources";
                    if ("/index".equals(url)) {
                        File resources = new File(basePath);
                        List<File> files = findAllFilenames(resources);
                        for (File file : files) {
                            Path relativePath = resources.toPath().relativize(file.toPath());
                            writer.write(relativePath.toString());
                            writer.write('\n');
                        }
                        continue;
                    }

//                    File targetFile = new File(basePath.resolve(Paths.get(url)).toAbsolutePath().toString());
                    File targetFile = new File(basePath + url);
                    if (targetFile.exists()) {
                        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(targetFile), 8192);) {
                            byte[] buffer = new byte[8192];
                            while (true) {
                                int len = inputStream.read(buffer);
                                if (len == -1) {
                                    break;
                                }
                                outputStream.write(buffer, 0, len);
                            }
                            outputStream.flush();
                        } catch (IOException ioException) {
//                            ??? 어떻게 처리할지 잘 모르겠음
//                            전송 중에 중단 됐을 때 데이터의 일부만 보내지게 되는데, 이를 client에 알리고 싶음
                            ioException.printStackTrace();
                            writer.write('\n');
                            writer.write("데이터에 일부만 전송되었습니다. 주의 바랍니다.");
                            writer.write('\n');
                        }
                        continue;
                    }

//                    파일이 없는 경우 분기처리
                    writer.write(MessageFormat.format("Filename ({0}) no exists", url));

//                    get method 분기처리
                    continue;
                }

//                Fail
                writer.write("http/1.1 400 Not Found\n");
                writer.write("\n");
                writer.write("Not found");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static List<File> findAllFilenames(File resources) {
        if (resources == null) {
            throw new RuntimeException();
        }

        if (!resources.isDirectory()) {
            throw new RuntimeException();
        }

        List<File> files = new ArrayList<>();
        fillFilenames(resources, files);

        return files;
    }

    private static void fillFilenames(File resources, List<File> files) {
        File[] targetFiles = resources.listFiles();

        for (File file : targetFiles) {
            if (file.isDirectory()) {
                fillFilenames(file, files);
                continue;
            }
            files.add(file);
        }
    }

    private static BufferedReader toReader(InputStream inputStream) {
        InputStream bis = new BufferedInputStream(inputStream, 8192);
        InputStreamReader reader = new InputStreamReader(bis, StandardCharsets.UTF_8);

        return new BufferedReader(reader, 8192);
    }
}
