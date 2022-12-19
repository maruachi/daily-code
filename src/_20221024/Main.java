package _20221024;

import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
//    message -> inputstream -> "enter" 단위로 끊어 읽음
    public static void main(String[] args) throws IOException {
        String string = "1233\n34555\n32466\n34\n234\n2";
//        String string = "123456";
        InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
        byte[] buffer = new byte[2];
        int poolSize = 3;
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFull = false;
        while (true) {
            int len = inputStream.read(buffer);
            if (len == -1) {
                if (!isFull) {
                    System.out.println(stringBuilder);
                }
                isFull = false;
                stringBuilder.setLength(0);
                break;
            }

            String stringBuffer = new String(buffer, 0, len, StandardCharsets.UTF_8);
            int newLineIndex = stringBuffer.indexOf('\n');

            if (newLineIndex != -1) {
                String frontString = stringBuffer.substring(0, newLineIndex);
                String backString = stringBuffer.substring(newLineIndex + 1, stringBuffer.length());

                if (!isFull && stringBuilder.length() + frontString.length() <= poolSize) {
                    stringBuilder.append(frontString);
                    System.out.println(stringBuilder);
                }
                stringBuilder.setLength(0);
                isFull = false;
                stringBuilder.append(backString);
                continue;
            }

            if (isFull) {
                continue;
            }

            if (stringBuilder.length() + stringBuffer.length() > poolSize) {
                isFull = true;
                continue;
            }
            stringBuilder.append(stringBuffer);

//            if (newLineIndex == -1) {
//                stringBuilder.append(stringBuffer);
//                continue;
//            }
//
//            String frontString = stringBuffer.substring(0, newLineIndex);
//            String backString = stringBuffer.substring(newLineIndex + 1, stringBuffer.length());
//
//            stringBuilder.append(frontString);
//
//            System.out.println(stringBuilder);
//            stringBuilder.setLength(0);
//
//            stringBuilder.append(backString);
        }
    }
}
