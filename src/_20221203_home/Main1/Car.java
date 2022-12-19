package _20221203_home.Main1;

import java.util.Objects;

public class Car implements Comparable {
    private final String name;
    private final int distance;

    public Car(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return distance == car.distance && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Car)) {
            throw new RuntimeException();
        }
        return ((Car) o).distance - this.distance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}
