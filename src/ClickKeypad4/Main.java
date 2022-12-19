package ClickKeypad4;

import java.util.ArrayList;
import java.util.List;

public class Main {
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
        List<KeyPosition> keys = new ArrayList<>();
        for(int number : numbers){
            keys.add(KeyPosition.createByKeyValue(Character.forDigit(number, 10)));
        }

        KeyFinder keyFinder = KeyFinder.createDefault();
        StringBuilder stringBuilder = new StringBuilder();

        KeyPosition rKey = KeyPosition.createByKeyValue('#');
        KeyPosition lKey = KeyPosition.createByKeyValue('*');

        for(KeyPosition key : keys){

            Side keySide = keyFinder.findSide(key);
            Side handSide = Side.valueOf(hand.toUpperCase());

            if(keySide == Side.MIDDLE && KeyPosition.isNotSameDistanceFrom(lKey, rKey, key))  {
                KeyPosition closerKey = KeyPosition.getCloserKeyFrom(lKey, rKey, key);
                handSide = closerKey.equals(lKey) ? Side.LEFT : Side.RIGHT;
            }

            if(keySide != Side.MIDDLE){
                handSide = keySide;
            }

            if(handSide == Side.RIGHT){
                rKey = key;
                stringBuilder.append('R');
            }else {
                lKey = key;
                stringBuilder.append('L');
            }
        }

        return stringBuilder.toString();
    }
}
