package _20221203_home.Main2;

import java.text.MessageFormat;
import java.util.Objects;
/*
* Car라는 객체는 속성을 이름과 운행거리를 갖는다.
* 이 속성값들은 보다 Car 객체에 어울리는 값이다.
* 운행 거리는 변경될 수 있는 값이기 때문에 final을 사용하지 않았다.
* 고정시켜서 사용하는 방법을 고민해보았으니 번거로워서 그 방향을 선택하지는 않았다.
* 대신에 moveOneUnitDistance 메소드를 통해서 그 사용을 제한했다.
* moveOneUnitDistance 메소드로 drivingDistance은 제멋대로 설정되는 것이 아니라, 제한적으로 변경되게 된다.
* final 보다는 약한 방법이기는 하나 단순히 사용측에서 값을 제멋대로 변경하는 것보다는 훨씬 나은 전략이다.
* */
public class Car implements Comparable{
    private final String name;
    private int drivingDistance;

    private Car(String name, int drivingDistance) {
        if (name == null) {
            throw new RuntimeException();
        }

        if (name.length() > 5 || name.length() == 0) {
            throw new RuntimeException("Not valid car name length");
        }

        if (drivingDistance < 0) {
            throw new RuntimeException("Not valid drivingDistance");
        }

        this.name = name;
        this.drivingDistance = drivingDistance;
    }

    public static Car createWithZeroDistance(java.lang.String name) {
        return new Car(name, 0);
    }

    public void moveOneUnitDistance() {
        this.drivingDistance++;
    }

    public boolean isSameDrivingDistanceWith(Car car) {
        return this.drivingDistance == car.drivingDistance;
    }

    public boolean isNotSameDrivingDistanceWith(Car car) {
        return !isSameDrivingDistanceWith(car);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return drivingDistance == car.drivingDistance && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, drivingDistance);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new RuntimeException();
        }

        if (!(o instanceof Car)) {
            throw new RuntimeException();
        }
        return ((Car) o).drivingDistance - this.drivingDistance;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(100);
        for (int i = 0; i < drivingDistance; i++) {
            stringBuilder.append('-');
        }
        return MessageFormat.format("{0} : {1}", name, stringBuilder);
    }
}
