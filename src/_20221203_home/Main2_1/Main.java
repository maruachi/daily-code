package _20221203_home.Main2_1;

import java.text.MessageFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<CarName> carNames = null;
        int N = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분");
            String lineWithCarNames = scanner.next();
            carNames = parseCarName(lineWithCarNames);

            System.out.println("시도횟수를 입력하시요.:");
            N = Integer.parseInt(scanner.next());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (carNames == null) {
            throw new RuntimeException();
        }

        if (N == 0 || carNames.isEmpty()) {
            return;
        }

        CarDistanceBoard carDistanceBoard = CarDistanceBoard.createWithZeroDistance(carNames);
        List<CarName> winnerCarNames = new ArrayList<>();
        for (CarName carName : carNames) {
            winnerCarNames.add(carName);
        }

        CarDistance winnerCarDistance = new CarDistance(0);
        Random random = new Random(0);

        for (int i = 0; i < N; i++) {
            for (CarName carName : carNames) {
                if (isMoved(random)) {
                    CarDistance newCarDistance = carDistanceBoard.get(carName).increaseOneUnit();
                    carDistanceBoard.update(carName, newCarDistance);

                    if (newCarDistance.isMoreThan(winnerCarDistance)) {
                        winnerCarDistance = newCarDistance;
                        winnerCarNames.clear();
                        winnerCarNames.add(carName);
                        continue;
                    }

                    if (winnerCarDistance.equals(newCarDistance)) {
                        winnerCarNames.add(carName);
                    }
                }
            }

            printCarDistanceByDash(carNames, carDistanceBoard);
        }

        System.out.println(winnerCarNames);

    }

    private static void printCarDistanceByDash(List<CarName> carNames, CarDistanceBoard carDistanceBoard) {
        for (CarName carName : carNames) {
            CarDistance carDistance = carDistanceBoard.get(carName);
            System.out.println(MessageFormat.format("{0} : {1}", carName, carDistance.convertToDash()));
        }
        System.out.println();
    }

    private static boolean isMoved(Random random) {
        return random.nextInt(10) >= 4;
    }

    private static List<CarName> parseCarName(String lineWithCarNames) {
        if (lineWithCarNames == null) {
            throw new RuntimeException();
        }

        List<CarName> carNames = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(lineWithCarNames, ",");
        while (stringTokenizer.hasMoreTokens()) {
            carNames.add(new CarName(stringTokenizer.nextToken().trim()));
        }

        return carNames;
    }
}
