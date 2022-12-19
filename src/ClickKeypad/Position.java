package ClickKeypad;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distanceFrom(Position other){
        return Math.abs((x - other.x))  + Math.abs((y - other.y));
    }
}
