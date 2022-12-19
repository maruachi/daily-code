package _20221203_home.Main2_1;

import java.util.Objects;
/*
* CarDistance는 자동차의 이동거리를 나타나내는 객체이다.
* 이동거리와 관련된 비교나 연산을 담당한다.
* */
public class CarDistance {
    private final int distance;

    public CarDistance(int distance) {
        if (distance < 0) {
            throw new RuntimeException();
        }
        this.distance = distance;
    }

    public CarDistance increaseOneUnit() {
        return new CarDistance(distance + 1);
    }

    public boolean isMoreThan(CarDistance carDistance) {
        return distance > carDistance.distance;
    }

    public String convertToDash() {
        StringBuilder stringBuilder = new StringBuilder(distance);
        for (int i = 0; i < distance; i++) {
            stringBuilder.append('-');
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDistance that = (CarDistance) o;
        return distance == that.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance);
    }
}
