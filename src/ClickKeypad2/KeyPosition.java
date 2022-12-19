package ClickKeypad2;

import java.util.HashMap;
import java.util.Map;

public class KeyPosition {
    private final Map<Key, Position> value;

    private KeyPosition(Map<Key, Position> value) {
        this.value = value;
    }

    public static KeyPosition createDefault(){
        Map<Key, Position> value = new HashMap<>();
        value.put(Key.createByChar('1'), new Position(0, 0));
        value.put(Key.createByChar('2'), new Position(1, 0));
        value.put(Key.createByChar('3'), new Position(2, 0));
        value.put(Key.createByChar('4'), new Position(0, 1));
        value.put(Key.createByChar('5'), new Position(1, 1));
        value.put(Key.createByChar('6'), new Position(2, 1));
        value.put(Key.createByChar('7'), new Position(0, 2));
        value.put(Key.createByChar('8'), new Position(1, 2));
        value.put(Key.createByChar('9'), new Position(2, 2));
        value.put(Key.createByChar('*'), new Position(0, 3));
        value.put(Key.createByChar('0'), new Position(1, 3));
        value.put(Key.createByChar('#'), new Position(2, 3));
        return new KeyPosition(value);
    }

    public Key compareCloserFrom(Key key1, Key key2, Key fromKey){
        Position keyPos1 = value.get(key1);
        Position keyPos2 = value.get(key2);
        Position fromKeyPos = value.get(fromKey);
        int dis1 = keyPos1.distanceFrom(fromKeyPos);
        int dis2 = keyPos2.distanceFrom(fromKeyPos);
        return  dis1 - dis2 > 0 ? key1 : key2;
    }
}
