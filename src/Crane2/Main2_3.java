package Crane2;

import java.util.Stack;

public class Main2_3 {
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
//        크레인 인형뽑기 게임 알고리즘에서 필요한 필수 operation을 정의 해봅시다.
//        NxN 격자에서 필요한 처리 요소는 무엇일까요?
//        1x1 격자에서 필요한 처리 요수는 무엇일까요?
//        NxN 격자에서 i번째 column에 상단에 있는 값 조회 및 삭제
//        grid -> i번째 getLastValueOfColumn(int colum)
//        LastValues previousLastValue

        int MAX_SIZE = board.length;
        Stack<Integer>[] grid = createGrid(board, MAX_SIZE);

        Stack<Integer> firstValues = new Stack<>();

        int recentFirstValue = 0;

        int[] columns = toColumns(moves, MAX_SIZE);
        int countDelete = 0;

        for (int column : columns) {
            int firstValue = getFirstValueOfColumn(grid, column);
//            get은 노력 없이 있는 걸 바로 가져오는 것
            deleteFirstValueOfColumn(grid, column);

            if(recentFirstValue != firstValue){
                firstValues.add(firstValue);
                recentFirstValue = firstValue;
                continue;
            }

            if(recentFirstValue == firstValue){
                deleteRecentFirstValue(firstValues);
                recentFirstValue = getRecentFirstValue(firstValues);
//                지우고 가져오는 건 이해가 안 가는 코드
//                조회하고 가져오는 건 자연스럽다
                countDelete += 2;
            }
        }

        return countDelete;
    }

    private static int getRecentFirstValue(Stack<Integer> firstValues) {
        return firstValues.empty() ? 0 : firstValues.peek();
//        0은 문제에서 주어진 값이기 때문에 파악하기 힘들다
    }

    private static int[] toColumns(int[] moves, int MAX_SIZE) {
        int[] columns = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            columns[i] = moves[i] - 1;
        }
        return columns;
    }

    private static void deleteRecentFirstValue(Stack<Integer> firstValues) {
        if(firstValues.empty()){
            return;
        }
        firstValues.pop();
    }

    private static void deleteFirstValueOfColumn(Stack<Integer>[] grid, int column) {
        if(grid[column].empty()){
            return;
        }
        grid[column].pop();
    }

    private static int getFirstValueOfColumn(Stack<Integer>[] grid, int column) {
        if(grid[column].empty()){
            return 0;
        }
        return grid[column].peek();
    }

    private static Stack<Integer>[] createGrid(int[][] board, int MAX_SIZE) {
        Stack<Integer>[] grid = new Stack[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            grid[i] = new Stack<>();
            for (int j = MAX_SIZE - 1; j >= 0; j--) {
                if(board[j][i] == 0){
                    break;
                }
                grid[i].add(board[j][i]);
            }
        }
        return grid;
    }
}
