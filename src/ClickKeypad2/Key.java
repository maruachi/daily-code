package ClickKeypad2;

import java.util.Objects;

public class Key {
    private static Key[] KEYS = new Key[]{
            new Key('0'),
            new Key('1'),
            new Key('2'),
            new Key('3'),
            new Key('4'),
            new Key('5'),
            new Key('6'),
            new Key('7'),
            new Key('8'),
            new Key('9'),
            new Key('*'),
            new Key('#')
    };

    private final char value;

    public Key(char value) {
        this.value = value;
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
