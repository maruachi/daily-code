package Crane2._20220915;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
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
        //stack 배열보다는 mapping하는 게 보기에 더 낫다 stack 배열은 무엇을 나타내는지 힌트가 되기 어렵다.
        Map<Integer, Dolls> dollsFinders = new HashMap<>();
        for (int column = 0; column < board.length; column++) {
            dollsFinders.put(column, Dolls.fromColumn(column, board));
        }

        Dolls dolls = Dolls.empty();
        int removeCount = 0;

        for (int move : moves) {
            int column = move - 1;
            Dolls targetDolls = dollsFinders.get(column);
            if (targetDolls.isEmpty()) {
                continue;
            }
            int doll = targetDolls.removeDoll();

            if (dolls.isEqualToPreviousDoll(doll)) {
                dolls.removeDoll();
                removeCount += 1;
                continue;
            }

            dolls.add(doll);
        }

        return removeCount * 2;
    }
}
