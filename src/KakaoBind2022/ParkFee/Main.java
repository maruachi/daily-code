package KakaoBind2022.ParkFee;

import sun.rmi.runtime.Log;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        int[] fees = new int[]{
                180,5000,10,600
        };
        String[] records = new String[]{
                "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
        };
        int[] result = solution(fees, records);
        System.out.println(Arrays.toString(result));
    }

    private static int[] solution(int[] fees, String[] records) {
        Map<String, LocalTime> carPool = new HashMap<>();
        Map<String, Integer> timeTable = new HashMap<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime endTime = LocalTime.parse("23:59", dateTimeFormatter);
//        System.out.println(endTime);

        for (String record : records) {
            StringTokenizer stringTokenizer = new StringTokenizer(record, " ");
            LocalTime time = LocalTime.parse(stringTokenizer.nextToken(), dateTimeFormatter);
            String carId = stringTokenizer.nextToken();
            String state = stringTokenizer.nextToken();

            if ("IN".equals(state) && !carPool.containsKey(carId)) {
                carPool.put(carId, time);
                continue;
            }

            if ("OUT".equals(state) && carPool.containsKey(carId)) {
                LocalTime inTime = carPool.get(carId);

                carPool.remove(carId);
                continue;
            }
        }
        return null;
    }
}