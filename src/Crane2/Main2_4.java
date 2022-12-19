package Crane2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main2_4 {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                new int[]{0,0,0,0,0},
                new int[]{0,0,1,0,3},
                new int[]{0,2,5,0,1},
                new int[]{4,2,4,4,2},
                new int[]{3,5,1,3,1}
        };

        int[] moves = new int[]{1,5,3,5,1,2,1,4};

        System.out.println(solution(board, moves));
    }

    private static int solution(int[][] board, int[] moves) {
        Stack<Integer> dolls = new Stack<>();
        int removeCount = 0;

        Map<Integer, Stack<Integer>> dollColumnFinders = createDollcolumnFinders(board);

        for (int column : moves) {
            Stack<Integer> dollColumn = dollColumnFinders.get(column);
            int dollValue = dollColumn.pop();

            if (isSameWithPreviousDoll(dolls, dollValue)) {
                dolls.pop();
                removeCount+=1;
                continue;
            }

            dolls.push(dollValue);
//
//            int findDollIndex = findDollIndex(dollColumnFinders, column);
//            int findDollValue = board[findDollIndex][column];
//            removeDollAt(board, findDollIndex);
//            if(isSameWithPreviousDoll(dolls, findDollValue)){
//                dolls.pop();
//                removeCount += 1;
////                답을 도출해내는 것이 굳이 로직에 포함될 필요가 없다.
//                continue;
//            }
//            dolls.push(findDollValue);
        }

        return removeCount*2;
    }

    private static Map<Integer, Stack<Integer>> createDollcolumnFinders(int[][] board) {
        Map<Integer, Stack<Integer>> dollColumFinders = new HashMap<>();
        for (int column = 0; column < board.length; column++) {
            Stack<Integer> dollColumn = createDollColumn(board, column);
            dollColumFinders.put(column, dollColumn);
        }
        return dollColumFinders;
    }

    private static Stack<Integer> createDollColumn(int[][] board, int column) {
        Stack<Integer> dollColumn = new Stack<>();
        for (int row = board.length-1; row >= 0; row--) {
            if(board[row][column] == 0){
                break;
            }
            dollColumn.push(board[row][column]);
        }
        return dollColumn;
    }

    private static boolean isSameWithPreviousDoll(Stack<Integer> dolls, int findDollValue) {
        return false;
    }

    private static void removeDollAt(int[][] board, int findDollIndex) {

    }

    private static int findDollIndex(int[][] board, int column) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] != 0) {
                return i;
            }
        }
        throw new RuntimeException();
//        return -1;
    }
}
