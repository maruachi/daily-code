package ClickKeypad4;

import java.util.HashMap;
import java.util.Map;

public class KeyFinder {
    private final Map<KeyPosition, Side> values;

    public KeyFinder(Map<KeyPosition, Side> values) {
        this.values = values;
    }

    public static KeyFinder createDefault(){
        Map<KeyPosition, Side> values = new HashMap<>();
        values.put(KeyPosition.createByKeyValue('1'),Side.LEFT);
        values.put(KeyPosition.createByKeyValue('2'),Side.MIDDLE);
        values.put(KeyPosition.createByKeyValue('3'),Side.RIGHT);
        values.put(KeyPosition.createByKeyValue('4'),Side.LEFT);
        values.put(KeyPosition.createByKeyValue('5'),Side.MIDDLE);
        values.put(KeyPosition.createByKeyValue('6'),Side.RIGHT);
        values.put(KeyPosition.createByKeyValue('7'),Side.LEFT);
        values.put(KeyPosition.createByKeyValue('8'),Side.MIDDLE);
        values.put(KeyPosition.createByKeyValue('9'),Side.RIGHT);
        values.put(KeyPosition.createByKeyValue('*'),Side.LEFT);
        values.put(KeyPosition.createByKeyValue('0'),Side.MIDDLE);
        values.put(KeyPosition.createByKeyValue('#'),Side.RIGHT);
        return new KeyFinder(values);
    }

    public Side findSide(KeyPosition key){
        return values.get(key);
    }
}