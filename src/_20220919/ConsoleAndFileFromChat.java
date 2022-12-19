package _20220919;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleAndFileFromChat {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        OutputStream sout = System.out;
        OutputStream fos = new FileOutputStream("fromChat.txt");
        byte[] newLineBytes = "\n".getBytes(StandardCharsets.UTF_8);
        while (true){
            String line = scanner.nextLine();
//            개항문자는 포함시키지 않음 -> 개행문자를 따로 추가해주어야 한다
//            혹은 한번에 가져오는 방법은 없을까?
            if(line.equals("quit")){
                System.exit(100);
            }
            byte[] byteData = line.getBytes(StandardCharsets.UTF_8);
            sout.write(byteData);
            sout.write(newLineBytes);
            fos.write(byteData);
            fos.write(newLineBytes);
        }
    }
}
