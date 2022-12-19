package _20221203_home.Main2_2;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<CarName> carNames = null;
        int N = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분");
            String lineWithCarNames = scanner.next();
//            carNames = parseCarName(lineWithCarNames);
            carNames = splitCarNames(lineWithCarNames).stream()
                    .map(CarName::new)
                    .collect(Collectors.toList());

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

        CarDistance winnerCarDistance = new CarDistance(0);
        Random random = new Random(0);

        for (int i = 0; i < N; i++) {
            for (CarName carName : carNames) {
                if (isMoved(random)) {
                    /*
                    * 기존에 maxCarDistance를 여기에서 update를 해서 Car을 움직이는 작업과 함께 다뤘다
                    * 별로 좋은 방법이 아닌 게 논리가 복잡해지고 독립적인 코딩이 아니기 때문이다.
                    * max값을 나중에 계산하여 아래를 빼도록 했고 max값을 계산하는 것을 CarDistanceBoard의 개념과 결합하였다.
                    * */
                    CarDistance carDistanceBeforeMove = carDistanceBoard.get(carName);
                    CarDistance carDistanceAfterMove = carDistanceBeforeMove.increaseOneUnit();
                    /*
                    * 결국엔 해야할 것이 무엇인가? -> 최대값 출력
                    * 최대값 찾아자는 객체 필요 -> carDTO carNames.getWithMaxDistance()
                    * 자료구조 MaxDistance or MaxDistance -> CarNames
                    * CarDistances -> getMax()
                    * */
                    carDistanceBoard.update(carName, carDistanceAfterMove);
                }
            }

            printCarDistanceByDash(carNames, carDistanceBoard);
        }

        CarDistance maxCarDistance = carDistanceBoard.getMax();
        List<CarName> winnerCarNames = new ArrayList<>();
        for (CarName carName : carNames) {
            if (maxCarDistance.equals(carDistanceBoard.get(carName))) {
                winnerCarNames.add(carName);
            }
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

    private static List<String> splitCarNames(String carNames) {
        StringTokenizer stringTokenizer = new StringTokenizer(carNames, ",");
        List<String> carNameStrings = new ArrayList<>(stringTokenizer.countTokens());

        while (stringTokenizer.hasMoreTokens()) {
            carNameStrings.add(stringTokenizer.nextToken());
        }

        return carNameStrings;
    }
}