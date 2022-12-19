package ClickKeypad;

import java.util.Objects;

public class Key {
    private static Key[] KEYS = new Key[]{
            new Key('0', new Position(2,3)),
            new Key('1', new Position(1,0)),
            new Key('2', new Position(2,0)),
            new Key('3', new Position(3,0)),
            new Key('4', new Position(1,1)),
            new Key('5', new Position(2,1)),
            new Key('6', new Position(3,1)),
            new Key('7', new Position(1,2)),
            new Key('8', new Position(2,2)),
            new Key('9', new Position(3,2)),
            new Key('*', new Position(1,3)),
            new Key('#', new Position(3,3))
    };

    private final char value;
    private final Position position;

    private Key(char value, Position position) {
        this.value = value;
        this.position = position;
    }

    public static Key createByChar(char value){
        int index = 0;

        if(value == '*'){
            index = 9;
        }
        else if(value == '#'){
            index = 10;
        }
        else{
            index = value - '0';
        }

        if(index < 0 || index >= KEYS.length){
            throw new RuntimeException();
        }

        return KEYS[index];
    }

    public int distanceFrom(Key key){
        return this.position.distanceFrom(key.position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return value == key.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
