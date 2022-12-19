package Crane1;

import java.text.MessageFormat;
import java.util.Stack;

public class Main1 {
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
        int removedNum = 0;

        for(int move : moves){
            //find the top doll
            for(int h = 0; h < board.length; h++){
                int newDoll = board[h][move-1];
                if(newDoll == 0){
                    continue;
                }
                if(!dolls.empty() && dolls.peek() == newDoll){
                    dolls.pop();
                    removedNum += 2;
                }
                else {
                    dolls.add(newDoll);
                }
                board[h][move-1] = 0;
                break;
            }
        }

        return removedNum;
    }
}
