package ClickKeypad;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        int[][] numbers = {{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
                {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}};
        String[] hand = {"right", "left", "right"};

        for(int t = 0; t < numbers.length; t++){
            System.out.println(solution(numbers[t], hand[t]));
        }
    }

    private static String solution(int[] numbers, String hand) {
        Map<Character, Side> keyFinder = createKeyFinder();

        Map<Character, Position> keyPosition = createKeyPosition();

        char rKey = '#';
        char lKey = '*';
        StringBuilder stringBuilder = new StringBuilder();
        for(int number : numbers){
            char key = Character.forDigit(number, 10);

            Side keySide = keyFinder.get(key);
            Side handSide = keySide;
            if(keySide == Side.MIDDLE){
                handSide = findHandSide(hand, keyPosition, rKey, lKey, key, handSide);
            }

            if(handSide == Side.RIGHT){
                rKey = key;
                stringBuilder.append('R');
            }
            else{
                lKey = key;
                stringBuilder.append('L');
            }
        }

        return stringBuilder.toString();
    }

    private static Side findHandSide(String hand, Map<Character, Position> keyPosition, char rKey, char lKey, char key, Side handSide) {
        Position rPos = keyPosition.get(rKey);
        Position lPos = keyPosition.get(lKey);
        Position currPos = keyPosition.get(key);
        int rDistance = rPos.distanceFrom(currPos);
        int lDistance = lPos.distanceFrom(currPos);

        if(hand == "right"){
            handSide = lDistance >= rDistance ? Side.RIGHT : Side.LEFT;
        }
        else if(hand == "left"){
            handSide = rDistance >= lDistance ? Side.LEFT : Side.RIGHT;
        }
        return handSide;
    }

    private static Map<Character, Position> createKeyPosition() {
        Map<Character, Position> keyPosition = new HashMap<>();

        keyPosition.put('1', new Position(0, 0));
        keyPosition.put('2', new Position(1, 0));
        keyPosition.put('3', new Position(2, 0));
        keyPosition.put('4', new Position(0, 1));
        keyPosition.put('5', new Position(1, 1));
        keyPosition.put('6', new Position(2, 1));
        keyPosition.put('7', new Position(0, 2));
        keyPosition.put('8', new Position(1, 2));
        keyPosition.put('9', new Position(2, 2));
        keyPosition.put('*', new Position(0, 3));
        keyPosition.put('0', new Position(1, 3));
        keyPosition.put('#', new Position(2, 3));
        return keyPosition;
    }

    private static Map<Character, Side> createKeyFinder() {
        Map<Character, Side> keyFinder = new HashMap<>();

        keyFinder.put('1', Side.LEFT);
        keyFinder.put('2', Side.MIDDLE);
        keyFinder.put('3', Side.RIGHT);
        keyFinder.put('4', Side.LEFT);
        keyFinder.put('5', Side.MIDDLE);
        keyFinder.put('6', Side.RIGHT);
        keyFinder.put('7', Side.LEFT);
        keyFinder.put('8', Side.MIDDLE);
        keyFinder.put('9', Side.RIGHT);
        keyFinder.put('*', Side.LEFT);
        keyFinder.put('0', Side.MIDDLE);
        keyFinder.put('#', Side.RIGHT);
        return keyFinder;
    }
}
