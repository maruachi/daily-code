package Crane2;

import java.util.Stack;

public class Main2_2 {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                new int[]{0,0,0,0,0},
                new int[]{0,0,1,0,3},
                new int[]{0,2,5,0,1},
                new int[]{4,2,4,4,2},
                new int[]{3,5,1,3,1}
        };

        int[] moves = new int[]{1,5,3,5,1,2,1,4};

        int MAX_SIZE = board.length;

        Stack<Integer>[] topPool = createTopPool(board, MAX_SIZE);
        int[] tops = createTops(MAX_SIZE, topPool);

        Stack<Integer> movedTopPool = new Stack<>();
        int topMatcher = 0;
        int deletedTop = 0;
        for(int move : moves){
            int moveIndex = move - 1;
            int moveTop = tops[moveIndex];
            if(moveTop == 0){
                continue;
            }

            updateTopsByIndex(tops, topPool, moveIndex);

            if (topMatcher != moveTop) {
                movedTopPool.add(moveTop);
                topMatcher = moveTop;
                continue;
            }

            if(topMatcher == moveTop){
                moveTop = 0; deletedTop += 1;
                movedTopPool.pop(); deletedTop += 1;
                topMatcher = movedTopPool.empty() ? 0 : movedTopPool.peek();
                continue;
            }
        }

        System.out.println(deletedTop);
    }

    private static int[] createTops(int MAX_SIZE, Stack<Integer>[] topPool) {
        int[] tops = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            updateTopsByIndex(tops, topPool, i);
        }
        return tops;
    }

    private static void updateTopsByIndex(int[] tops, Stack<Integer>[] topPool, int i) {
        if(topPool[i].empty()){
            tops[i] = 0;
            return;
        }

        if(!topPool[i].empty()) {
            tops[i]= topPool[i].pop();
            return;
        }
    }

    static Stack<Integer>[] createTopPool(int[][] board, int MAX_SIZE) {
        Stack<Integer>[] topPool = new Stack[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            topPool[i] = new Stack<>();
            for (int h = MAX_SIZE - 1; h >= 0; h--) {
                if(board[h][i] == 0){
                    break;
                }
                topPool[i].add(board[h][i]);
            }
        }
        return topPool;
    }
}
