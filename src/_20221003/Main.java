package _20221003;//package _20221003;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        InputStream sin = System.in;
//        BufferedInputStream bis = new BufferedInputStream(sin, 8192);
//        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
//        BufferedReader br = new BufferedReader(isr, 8192);
//
//        OutputStream original = new FileOutputStream("original.txt");
//        BufferedOutputStream bosOriginal = new BufferedOutputStream(original, 8192);
//        OutputStreamWriter oswOriginal = new OutputStreamWriter(bosOriginal, StandardCharsets.UTF_8);
//
//        OutputStream change = new FileOutputStream("change.txt");
//        BufferedOutputStream bosChange = new BufferedOutputStream(change, 8192);
//        OutputStreamWriter oswChange = new OutputStreamWriter(bosChange, StandardCharsets.UTF_8);
//        // 버퍼 처리, 인코딩/디코딩, 해제처리
//        while (true) {
//            String line = br.readLine();
//
//            if ("q".equals(line)) {
//                break;
//            }
//
//            String subLine = line;
//            while (true) {
//                int index = subLine.indexOf("한글");
//                if (index == -1) {
//                    oswOriginal.write(subLine);
//                    oswChange.write(subLine);
//                }
//
//                oswOriginal.write(subLine, 0, index);
//                oswOriginal.write("한글");
//                oswChange.write(subLine, 0, index);
//                oswChange.write("abcd");
//
//                subLine = subLine.substring(0, )
//            }
//
//            int index = line.indexOf("한글");
//            if (index == -1) {
//                oswOriginal.write(line);
//                oswChange.write(line);
//                continue;
//            }
//
//
//        }
//    }
//}
