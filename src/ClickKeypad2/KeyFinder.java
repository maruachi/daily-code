package ClickKeypad2;

import java.util.HashMap;
import java.util.Map;

public class KeyFinder {
    private Map<Key, Side> values;

    public KeyFinder(Map<Key, Side> values) {
        if(values == null){
            throw new RuntimeException();
        }
        this.values = values;
    }

    public static KeyFinder create(){
        Map<Key, Side> values = new HashMap<>();
        values.put(Key.createByChar('1'), Side.LEFT);
        values.put(Key.createByChar('2'), Side.MIDDLE);
        values.put(Key.createByChar('3'), Side.RIGHT);
        values.put(Key.createByChar('4'), Side.LEFT);
        values.put(Key.createByChar('5'), Side.MIDDLE);
        values.put(Key.createByChar('6'), Side.RIGHT);
        values.put(Key.createByChar('7'), Side.LEFT);
        values.put(Key.createByChar('8'), Side.MIDDLE);
        values.put(Key.createByChar('9'), Side.RIGHT);
        values.put(Key.createByChar('*'), Side.LEFT);
        values.put(Key.createByChar('0'), Side.MIDDLE);
        values.put(Key.createByChar('#'), Side.RIGHT);

        return new KeyFinder(values);
    }

    public Side findSide(Key key){
        return values.get(key);
    }

}
