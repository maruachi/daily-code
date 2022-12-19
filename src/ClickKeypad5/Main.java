package ClickKeypad5;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";

        char leftCursor = '*';
        char rightCursor = '#';
        char[] targetCursors = new char[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            targetCursors[i] = (char) (numbers[i] + '0');
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char targetCursor : targetCursors){
            if(isLeftCursorMoved(targetCursor)){
                leftCursor = setLeft(stringBuilder, targetCursor);
                continue;
            }

            if(isRightCursorMoved(targetCursor)){
                rightCursor = setRight(stringBuilder, targetCursor);
                continue;
            }

            MoveType moveType = findWhichCursorMove(targetCursor, leftCursor, rightCursor);

            if(moveType == MoveType.LEFT){
                leftCursor = setLeft(stringBuilder, targetCursor);
                continue;
            }

            if(moveType == MoveType.RIGHT){
                rightCursor = setRight(stringBuilder, targetCursor);
                continue;
            }

            if(moveType == MoveType.DEFALUT && hand.equals("left")){
                leftCursor = setLeft(stringBuilder, targetCursor);
                continue;
            }

            rightCursor = setRight(stringBuilder, targetCursor);
        }

        System.out.println(stringBuilder);
    }

    private static char setRight(StringBuilder stringBuilder, char targetCursor) {
        char rightCursor;
        rightCursor = targetCursor;
        stringBuilder.append('R');
        return rightCursor;
    }

    private static char setLeft(StringBuilder stringBuilder, char targetCursor) {
        char leftCursor;
        leftCursor = targetCursor;
        stringBuilder.append('L');
        return leftCursor;
    }

    private static MoveType findWhichCursorMove(char targetCursor, char leftCursor, char rightCursor) {
        Map<Character, int[]> cursorPositionMapper = new HashMap<>();
        cursorPositionMapper.put('1', new int[]{0, 0});
        cursorPositionMapper.put('2', new int[]{0, 1});
        cursorPositionMapper.put('3', new int[]{0, 2});
        cursorPositionMapper.put('4', new int[]{1, 0});
        cursorPositionMapper.put('5', new int[]{1, 1});
        cursorPositionMapper.put('6', new int[]{1, 2});
        cursorPositionMapper.put('7', new int[]{2, 0});
        cursorPositionMapper.put('8', new int[]{2, 1});
        cursorPositionMapper.put('9', new int[]{2, 2});
        cursorPositionMapper.put('*', new int[]{3, 0});
        cursorPositionMapper.put('0', new int[]{3, 1});
        cursorPositionMapper.put('#', new int[]{3, 2});

        int[] leftCursorPosition = cursorPositionMapper.get(leftCursor);
        int[] rightCursorPosition = cursorPositionMapper.get(rightCursor);
        int[] targetCursorPosition = cursorPositionMapper.get(targetCursor);
        int distanceFromLeftCursor = computeDistance(targetCursorPosition, leftCursorPosition);
        int distanceFromRightCursor = computeDistance(targetCursorPosition, rightCursorPosition);

        if (distanceFromLeftCursor == distanceFromRightCursor) {
            return MoveType.DEFALUT;
        }

        return distanceFromLeftCursor < distanceFromRightCursor ? MoveType.LEFT : MoveType.RIGHT;
    }

    private static int computeDistance(int[] targetCursorPosition, int[] cursorPosition) {
        return Math.abs(targetCursorPosition[0] - cursorPosition[0]) + Math.abs(targetCursorPosition[1] - cursorPosition[1]);
    }

    private static boolean isRightCursorMoved(char targetCursor) {
        return targetCursor == '3' || targetCursor == '6' || targetCursor == '9';
    }

    private static boolean isLeftCursorMoved(char targetCursor) {
        return targetCursor == '1' || targetCursor == '4' || targetCursor == '7';
    }

    enum MoveType{
        RIGHT, LEFT, DEFALUT
    }
}
