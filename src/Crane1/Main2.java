package Crane1;

import java.util.Stack;

public class Main2 {
    private static Stack<Integer> dolls = new Stack<>();

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
        int removedNum = 0;
        Stack<Integer>[] tops = initTops(board);

        for(int move : moves){
            //find the top doll
            int indexCol = move - 1;
            if(tops[indexCol].empty()){
                continue;
            }
            int newDoll = tops[indexCol].peek();

            removedNum += pileUpDoll(newDoll);

            tops[indexCol].pop();
        }

        return removedNum;
    }

    private static int pileUpDoll(int newDoll) {
        int removedNum = 0;
        if(isSamwWithLastOne(newDoll)){
            dolls.pop();
            removedNum = 2;
        }
        else {
            dolls.add(newDoll);
        }
        return removedNum;
    }

    private static Stack<Integer>[] initTops(int[][] board) {
        int N = board.length;
        Stack<Integer>[] tops = new Stack[N];

        for(int i = 0; i < N; i++){
            tops[i] = new Stack<>();
        }

        for(int col = 1; col <= N; col++){
            int indexCol = col - 1;
            for(int floor = 1; floor <= N; floor++){
                int indexFloor = N - floor;
                int dollNum = board[indexFloor][indexCol];
                if(doesNotExist(dollNum)){
                    break;
                }
                tops[indexCol].add(dollNum);
            }
        }
        return tops;
    }

    private static boolean isSamwWithLastOne(int newDoll) {
        return !dolls.empty() && dolls.peek() == newDoll;
    }

    private static boolean doesNotExist(int newDoll) {
        return newDoll == 0;
    }
}
