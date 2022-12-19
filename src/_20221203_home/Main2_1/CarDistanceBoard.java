package _20221203_home.Main2_1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* CarDistanceMapper는 CarName -> CarDistance로 Mapping해주는 역할을 담당한다.
* 현재 Car의 이동거리가 몇인지 알 수 있다.
* 그리고 Car가 이동거리가 변경됐을 때 update도 기능을 제공한다.
* */
public class CarDistanceBoard {
    private final Map<CarName, CarDistance> value;

    private CarDistanceBoard(Map<CarName, CarDistance> value) {
        if (value == null) {
            throw new RuntimeException();
        }
        this.value = value;
    }

    public static CarDistanceBoard createWithZeroDistance(List<CarName> carNames) {
        Map<CarName, CarDistance> value = new HashMap<>();
        for (CarName carName : carNames) {
            value.put(carName, new CarDistance(0));
        }
        return new CarDistanceBoard(value);
    }

    public void update(CarName carName, CarDistance newCarDistance) {
        if (!value.containsKey(carName)) {
            throw new RuntimeException();
        }

        value.replace(carName, newCarDistance);
    }

    public CarDistance get(CarName carName) {
        if (!value.containsKey(carName)) {
            throw new RuntimeException();
        }
        return value.get(carName);
    }
}
