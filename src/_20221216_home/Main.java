package _20221216_home;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket = createServerSocket(7777);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 InputStreamReader isr = new InputStreamReader(new BufferedInputStream(socket.getInputStream(), 8192), StandardCharsets.UTF_8);
                 BufferedReader reader = toReader(socket.getInputStream());
                 BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream(), 8192);
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            ) {
//            read being line
                String beginLine = reader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(beginLine, " ");
                String version = stringTokenizer.nextToken();
                String method = stringTokenizer.nextToken();
                String url = stringTokenizer.nextToken();

//            read header
                HashMap<String, String> headers = new HashMap<>();
                while (reader.ready()) {
                    String line = reader.readLine();
                    if ("".equals(line)) {
                        break;
                    }
                    StringTokenizer headerTokenizer = new StringTokenizer(line, ":");
                    String headerKey = headerTokenizer.nextToken();
                    String headerValue = headerTokenizer.nextToken();
                    headers.put(headerKey, headerValue);
                }

//            read body
                StringBuilder body = new StringBuilder();
                char[] cbuffer = new char[8192];
                while (reader.ready()) {
                    int len = isr.read(cbuffer);
                    if (len == -1) {
                        break;
                    }
                    body.append(cbuffer, 0, len);
                }

                String path = new StringBuilder().append(System.getProperty("user.dir")).append("/Resources").toString();
//            handling request index
                if ("GET".equals(method) && "/index".equals(url)) {
                    File resourcePath = new File(path);
                    if (!resourcePath.isDirectory()) {
                        continue;
                    }

                    String[] filenames = resourcePath.list();

                    writer.write("Http/1.1 200 OK\n");
                    writer.write('\n');

                    for (String filename : filenames) {
                        List<String> fileResources = findFileResources(resourcePath.getAbsolutePath() + "/" + filename);

                        for (String fileResource : fileResources) {
                            writer.write(fileResource);
                            writer.write('\n');
                        }
                    }

                    writer.flush();
                    continue;
                }

//            handling request filename
                String filename = url;
                File sourceFile = new File(path + filename);
                if (!sourceFile.exists()) {
                    continue;
                }
                writer.write("Http/1.1 200 OK\n");
                writer.write('\n');
                writer.flush();
                try (InputStream sourceStream = new BufferedInputStream(new FileInputStream(path + filename), 8192)) {
                    byte[] buffer = new byte[8192];
                    while (true) {
                        int len = sourceStream.read(buffer);
                        if (len == -1) {
                            break;
                        }
                        outputStream.write(buffer, 0, len);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                outputStream.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }

    private static List<String> findFileResources(String filename) {
        Path path = Paths.get(filename);
        List<String> fileResources = new ArrayList<>();
        Path parentPath = path.getParent();
        fillFileResources(parentPath, path, fileResources);

        return fileResources;
    }

    private static void fillFileResources(Path basePath, Path path, List<String> fileResources) {
        if (Files.isDirectory(path)) {
            try {
                List<Path> files = Files.list(path).collect(Collectors.toList());
                for (Path file : files) {
                    fillFileResources(basePath, file, fileResources);
                }
            } catch (IOException ioException) {
                return;
            }
            return;
        }

        fileResources.add(basePath.relativize(path).toString());
    }

    private static ServerSocket createServerSocket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private static BufferedReader toReader(InputStream inputStream) {
        BufferedInputStream bis = new BufferedInputStream(inputStream, 8192);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);

        return new BufferedReader(isr, 8192);
    }
}
