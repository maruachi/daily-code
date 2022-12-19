package ClickKeypad4;

import java.util.Objects;

public class KeyPosition {
    private static final KeyPosition KEYS[] = new KeyPosition[]{
            new KeyPosition(3,1,'0'),
            new KeyPosition(0,0,'1'),
            new KeyPosition(0,1,'2'),
            new KeyPosition(0,2,'3'),
            new KeyPosition(1,0,'4'),
            new KeyPosition(1,1,'5'),
            new KeyPosition(1,2,'6'),
            new KeyPosition(2,0,'7'),
            new KeyPosition(2,1,'8'),
            new KeyPosition(2,2,'9'),
            new KeyPosition(3,0,'*'),
            new KeyPosition(3,2,'#')
    };

    private final int x;
    private final int y;
    private final char keyValue;

    private KeyPosition(int x, int y, char keyValue) {
        this.x = x;
        this.y = y;
        this.keyValue = keyValue;
    }

    public static KeyPosition createByKeyValue(char keyValue){
        int index = 0;

        if(keyValue == '*'){
            index = 10;
        }
        else if(keyValue == '#'){
            index = 11;
        }
        else{
            index = keyValue - '0';
        }

        if(index < 0 || index >= KEYS.length){
            throw new RuntimeException();
        }

        return KEYS[index];
    }

    private int distanceFrom(KeyPosition other){
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public static boolean isSameDistanceFrom(KeyPosition key1, KeyPosition key2, KeyPosition from){
        int dis1 = key1.distanceFrom(from);
        int dis2 = key2.distanceFrom(from);

        return dis1 == dis2;
    }

    public static boolean isNotSameDistanceFrom(KeyPosition key1, KeyPosition key2, KeyPosition from){
        return !isSameDistanceFrom(key1, key2, from);
    }

    public static KeyPosition getCloserKeyFrom(KeyPosition key1, KeyPosition key2, KeyPosition from){
        int dis1 = key1.distanceFrom(from);
        int dis2 = key2.distanceFrom(from);

        return dis1 <= dis2 ? key1 : key2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyPosition that = (KeyPosition) o;
        return x == that.x && y == that.y && keyValue == that.keyValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, keyValue);
    }
}
