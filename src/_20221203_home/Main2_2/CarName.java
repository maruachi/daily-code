package _20221203_home.Main2_2;

import java.util.Objects;

/*
* Car의 이름을 저장하고 각 개별 차를 식별하는 역할을 한다.
* */
public class CarName {
    private final String name;

//    trim을 여기에서 수행해야 한다.
//    splitCarName은 단순히 데이터를 나누는 역할만 수행한다.
//    length() == 0은 blank를 허용하는 코딩이다.
//    다른말로 하면 input에서 trim을 기대하는 코딩이다.
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
