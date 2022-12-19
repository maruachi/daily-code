package _20221203_home.Main1;

import java.text.MessageFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String line = null;
        int N = 0;
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분\n");
            line = scanner.next();
            System.out.println("시도횟수를 입력하시요.:");
            N = Integer.parseInt(scanner.next());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (line == null) {
            throw new RuntimeException();
        }

        if (N == 0) {
            return;
        }

        StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
        Map<String, Integer> distances = new HashMap<>(Collections.emptyMap());
        while (stringTokenizer.hasMoreTokens()) {
            distances.put(stringTokenizer.nextToken(), 0);
        }

        Random random = new Random(0);
        for (int i = 0; i < N; i++) {
            for (String carName : distances.keySet()) {
                int val = random.nextInt(10);

                if (val >= 4) {
                    distances.replace(carName, distances.get(carName) + 1);
                }
            }

            for (String carName : distances.keySet()) {
                StringBuilder stringBuilder = new StringBuilder(100);
                for (int j = 0; j < distances.get(carName); j++) {
                    stringBuilder.append('-');
                }
                System.out.println(MessageFormat.format("{0} : {1}", carName, stringBuilder));
            }
            System.out.println();
        }

        List<Car> carList = new ArrayList<>();
        for (String carName : distances.keySet()) {
            carList.add(new Car(carName, distances.get(carName)));
        }
        Collections.sort(carList);

        List<Car> winners = new ArrayList<>();
        int winDistance = carList.get(0).getDistance();
        for (Car car : carList) {
            if (car.getDistance() != winDistance) {
                break;
            }
            winners.add(car);
        }

        System.out.println("winners: " + winners);
    }
}
