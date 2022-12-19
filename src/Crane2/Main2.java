package Crane2;//package Crane2;
//
//import java.util.Stack;
//
//public class Main2 {
//    public static void main(String[] args) {
//        int[][] board = new int[][]{
//                new int[]{0,0,0,0,0},
//                new int[]{0,0,1,0,3},
//                new int[]{0,2,5,0,1},
//                new int[]{4,2,4,4,2},
//                new int[]{3,5,1,3,1}
//        };
//
//        int[] moves = new int[]{1,5,3,5,1,2,1,4};
//
//        int MAX_SIZE = board.length;
//        Stack<Integer>[] topPool = createTopPool(board, MAX_SIZE);
//
//        int[] tops = new int[MAX_SIZE];
//        initTops(tops, MAX_SIZE, topPool);
//
//        int count = 0;
//        int topMatcher = 0;
//        Stack<Integer> topMatcherPool = new Stack<>();
//
//        for (int move : moves) {
//            int i = move - 1;
//            if (isMatchedTop(topMatcher, tops[i])) {
//
//            }
//
//            updateTopsByIndex(tops, i, topPool);
//        }
//
//        System.out.println(count);
//    }
//
//    private static int getNewTopMatcher(Stack<Integer> topMatcherPool) {
//        int topMatcher = 0;
//        if (!topMatcherPool.empty()) {
//            topMatcher = topMatcherPool.pop();
//        }
//        return topMatcher;
//    }
//
//    private static boolean isMatchedTop(int topMatcher, int tops) {
//        return topMatcher != 0 && topMatcher == tops;
//    }
//
//    private static void updateTopsByIndex(int[] tops, int i, Stack<Integer>[] topPool ) {
//        if(topPool[i].empty()){
//            return;
//        }
//        tops[i] = topPool[i].pop();
//    }
//
//    private static int[] initTops(int[] tops, int MAX_SIZE, Stack<Integer>[] topPool) {
//        for (int i = 0; i < MAX_SIZE; i++) {
//            updateTopsByIndex(tops, i, topPool);
//        }
//        return tops;
//    }
//
//    private static Stack<Integer>[] createTopPool(int[][] board, int MAX_SIZE) {
//        Main2_2.createTopPool(board, MAX_SIZE);
//
//        return topPool;
//    }
//
//    private static int[] initTops(int[][] board, int MAX_SIZE) {
//        int[] matchers = new int[MAX_SIZE];
//        for (int i = 0; i < MAX_SIZE; i++) {
//            for (int j = 0; j < MAX_SIZE; j++) {
//                if(board[j][i] != 0){
//                    matchers[i] = board[j][i];
//                    break;
//                }
//            }
//        }
//        return matchers;
//    }
//}
