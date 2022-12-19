package _20221203_home.Main2_1;

import java.util.Objects;

/*
* Car의 이름을 저장하고 각 개별 차를 식별하는 역할을 한다.
* */
public class CarName {
    private final String name;

    public CarName(String name) {
        if (name == null) {
            throw new RuntimeException();
        }
        if (name.length() == 0 || name.length() > 5) {
            throw new RuntimeException();
        }

        this.name = name;
    }

    @Override
    public String toString() {
        return "CarName{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarName carName = (CarName) o;
        return Objects.equals(name, carName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
