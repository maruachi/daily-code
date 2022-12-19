package ClickKeypad2;

import ClickKeypad.Side;

public class Main3 {
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
        return null;
    }
}
