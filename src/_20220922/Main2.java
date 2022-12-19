package _20220922;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main2 {
    public static void main(String[] args) throws IOException {
//        String string = "hello IO";
//
//        OutputStream outputStream = new FileOutputStream("outfile2");
//        OutputStream bos = new BufferedOutputStream(outputStream, 10);
//        OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);

//        osw.write(string);
//        osw.flush();
//        osw.close();

//        InputStream inputStream = new FileInputStream("outfile2");
//        InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//        char[] cbuf = new char[2];
//        StringBuilder stringBuilder = new StringBuilder();
//        while (true) {
//            int len = isr.read(cbuf);
//            if (len == -1) {
//                break;
//            }
//            stringBuilder.append(cbuf, 0, len);
//        }
//        System.out.println(stringBuilder);

        InputStream inputStream = new FileInputStream("outfile2");
        InputStream bis = new BufferedInputStream(inputStream, 10);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
//        inputstream의 버퍼는 원천소스에서 얼마나 떼오는가?
//        bufferedReader에 있는 버퍼는 inputstream으로 부터 얼마나 가져오는가?
//        그렇기 때문에 bufferedReader의 버퍼가 더 크거나 같다.

        BufferedReader br = new BufferedReader(isr, 20);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("outfile3"), StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter, 20);
//        outputStreamWriter.write();
    }
}
