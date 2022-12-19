package _20221117;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) throws IOException {
        String string = "한H";
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);

        OutputStream outputStream = System.out;
        outputStream.write(bytes);
//        System.out.println(Arrays.toString(bytes));
    }
}
