package _20221203_home.Main2;

import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        * 자동차 이름과 시도횟수를 받아서 parsing하는 부분
        * pasrsing과 car객체 생성을 분리함
        * 그 이유는 car객체는 distance에 대한 정보를 요구하는데, parseCarName이 아닌 parseCar를 통해서 car객체를 바로 넘겨주면 parsing에 distance 정보가 들어가게 됨
        * 그런데 실제로 line을 받을 때 distance 정보는 가지고 있지가 않음
        * 그래서 parseCarName으로 정확히 input에서 주어진 정보만 parsing해서 가져오는 게 완전히 독립적이 코딩이다.
        * 시도 횟수도 마찬가지임
        * 논리 구조가 명확하게 보임 input을 받고 그것을 사용하기 위해서 parsing하여 자료 구조에 넣는 형태임
        * try-resources문을 사용했는데, scanner에 자원 해제 처리를 생각함
        * 그리고 예외 발생시 프로그램을 종료시켰음 그 이유가 정확한 input 정보가 들어오지 않으면 프로그램이 실행될 수 없기 때움임
        * */
        List<String> carNames = null;
        int N = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분");
            carNames = parseCarName(scanner.next());

            System.out.print("시도횟수를 입력하시요.:");
            N = Integer.parseInt(scanner.next());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        /*
        * carNames과 N값이 제대로 parsing이 되었고 유효한 값인지 검사하는 구간이다.
        * 시도 횟수가 0이거나 경주할 자동차가 없으면 프로그램을 터트리지 않고 종료
        * 왜냐하면 실제 진행 가능한 시나리오이기 때문이다. handling이 가능한 시나리오면 처리해주는 게 좋다.
        * 하지만 carNmaes이 null로 들어온 경우에는 시스템적으로 문제가 있는 상황이다. 요구사항에서 주어진 시나리오와는 관계가 없다.
        * 그렇기 때문에 단순히 프로그램을 끝내는 것이 아니라 문제 상황이 발생했다고 터트리는 것이 낫다.
        * 유효성 검사를 통해 시도횟수는 최소 1번 자동차이름은 최소 한 개 이상을 보장한다.
        * */
        if (carNames == null) {
            throw new RuntimeException();
        }

        if (carNames.size() == 0 || N == 0) {
            return;
        }

        /*
        * car 객체를 생성하는 구간이다.
        * 추후에 정렬을 위해 List 자료 구조를 사용했다.
        * carName을 받는 것과 car를 생성하는 것을 분리하는 이유는 drivingDistance 정보를 car 객체가 다루고 있고 drivingDistance가 carName하고 어떤 조합으로 들어가는지 분명히 독립적으로 명시하기 위해서다.
        * 이런 이유로 ZeroDrivingDistance와 같은 명확한 표현을 써서 생성되는 car객체의 drivingDistance가 무엇인지 표기하여 생성했다.
        * */
        List<Car> cars = createCarsWithZeroDrivingDistance(carNames);

        /*
        * driveRandomCars는 문제의 요구사항을 단순히 wrapping한 것이다.
        * 매 시도 횟수에 수행해야 하는 논리적인 과제가 무엇인지를 명확히 표현했다.
        * 이렇게 함수를 wrapping함으로써 시도 횟수가 가지는 정의가 분명히 드러난다.
        * 한번 시도는 아래의 무작위로 자동차를 움직이고 결과를 출력한다.
        * 단순히 함수로 wrapping해주는 것으로 논리적인 흐름을 명확하게 파악할 수 있다.
        * */
        for (int i = 0; i < N; i++) {
            driveRandomCars(cars);
            printCars(cars);
        }

        /*
        * 가장 멀리간 자동차가 무엇인지를 찾는 부분이다.
        * 정렬 알고리즘을 통해서 문제를 해결했다.
        * 단슨 max값을 찾는 것이 아니라 정렬을 사용한 이유는 중복된 거리를 가진 자동차를 파악하기 위해서이다.
        * 중복된 거리를 가진 자동차를 찾는 것을 분명하게 표현하기 위해 SameDrivingDistance라는 표현을 썼다.
        * 아래의 함수를 findAllTopRankingCars와 같이 전체의 부분 wrapping할 수 있는데 그렇겧 하지 않았다.
        * 그 이유는 논리적인 풀이 과정을 명시하고 싶기 때문이다.
        * sortedCars.get(0)이 가능한 이유는 위에서 유효성 검사를 통과했기 때문이다. 최소 차가 하나 존재할 것이라는 것이 보장된 상황이다.
        * 그런데 이 코드를 함수로 wrapping하지 않고 main에서 드러낸 이유기도하다. main에서 유효성 검사를 통해 최소 하나의 값이 존재하는 것을 보장한다는 것을 보다 쉽게 찾도록 하기 위함이다.
        * */
        List<Car> sortedCars = new ArrayList<>(cars);
        Collections.sort(sortedCars);


        Car TopRankingCar = sortedCars.get(0);
        List<Car> topRankingCars = findCarsSameDrivingDistanceWith(sortedCars, TopRankingCar);

        System.out.println(topRankingCars);
    }

    private static List<Car> findCarsSameDrivingDistanceWith(List<Car> sortedCars, Car topRankingCar) {
        /*
        * 이함수가 사용될 때 topRankingCar는 null이 아니라는 것은 보장하지만 이 함수가 독립적으로 사용될라면 유효성 검사를 해주는 게 좋다.
        * 나중에 이 함수가 활용될 때 아래와 같이 방어적으로 코딩하면 보다 에러가 적은 단단한 코딩이 가능하다.
        * */
        if (topRankingCar == null) {
            throw new RuntimeException();
        }

        List<Car> topRankingCars = new ArrayList<>();
        for (Car car : sortedCars) {
            if (topRankingCar.isNotSameDrivingDistanceWith(car)) {
                break;
            }
            topRankingCars.add(car);
        }

        return topRankingCars;
    }

    private static void printCars(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
        System.out.println();
    }

    private static void driveRandomCars(List<Car> cars) {
        Random random = new Random(LocalTime.now().getSecond());
        for (Car car : cars) {
            int randomValue = random.nextInt(10);
            /*
            * 아래와 같이 움직일지 말지 결정하는 코드를 isMoved로 wrapping하였다.
            * 4보다 크면 움직인다는 조건은 요구사항에서 원하는 코드이기 때문에 이것은 car에 메소드로 넣지는 않았다.
            * 그리고 함수로 wrapping한 이유는 요구사항 조건을 독립적으로 관리하기 위해서다.
            * 4보다 크다라는 것 조건은 언제든지 바뀔 수 있는데, 이를 함수가 아닌 driveRandomCars라는 전체 세션 흐름에 관련된 코드에 넣어서 관리하면 전체 세션을 다 파악해야 4보다 크다라는 조건에 의해서 움직이는 것이 결정된다고 이해하게 된다.
            * 이는 유지보수 시에 어려운 코딩이 된다.
            * */
            if (isMoved(randomValue)) {
                car.moveOneUnitDistance();
            }
        }
    }

    private static boolean isMoved(int randomValue) {
        return randomValue >= 4;
    }

    private static List<Car> createCarsWithZeroDrivingDistance(List<String> carNames) {
        List<Car> cars = new ArrayList<>();
        for (String carName : carNames) {
            /*
            * createWithZeroDistance라는 factory 메소드를 이용해서 zero distance라는 것에 의미를 분명히 했다.
            * new Car(carName, 0)보다는 더 명시적이다.
            * */
            cars.add(Car.createWithZeroDistance(carName));
        }
        return cars;
    }

    private static List<String> parseCarName(String lineWithCarName) {
        List<String> carNames = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(lineWithCarName, ",");

        while (stringTokenizer.hasMoreTokens()) {
            carNames.add(stringTokenizer.nextToken().trim());
        }

        return carNames;
    }
}
