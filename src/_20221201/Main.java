package _20221201;

import com.sun.corba.se.spi.orbutil.fsm.InputImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket = createServerSocket(7777);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader reader = toReader(socket.getInputStream());
                 OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream())) {
                String beginLine = reader.readLine();
                HttpBeginLine httpBeginLine = HttpBeginLine.parse(beginLine);

                HttpHeader httpHeader = parseHttpHeader(reader);
                StringBuilder httpBody = parseHttpBody(reader);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    private static StringBuilder parseHttpBody(BufferedReader reader) throws IOException {
        StringBuilder httpBody = new StringBuilder(10000);
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            httpBody.append(line).append('\n');
        }

        return httpBody;
    }

    private static HttpHeader parseHttpHeader(BufferedReader reader) {
        HttpHeader httpHeader = HttpHeader.createEmpty();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (line == null) {
                break;
            }

            if ("".equalsIgnoreCase(line)) {
                break;
            }
            httpHeader = httpHeader.with(line);
        }

        return httpHeader;
    }

    private static BufferedReader toReader(InputStream inputStream) {
        InputStream bis = new BufferedInputStream(inputStream, 8192);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);

        return new BufferedReader(isr, 8192);
    }

    private static ServerSocket createServerSocket(int port) {
        try {
            return new ServerSocket(port);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException(ioException);
        }
    }
}
