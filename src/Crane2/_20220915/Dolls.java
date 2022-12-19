package Crane2._20220915;

import java.sql.Statement;
import java.util.Stack;

public class Dolls {
    private final Stack<Integer> values;

    public Dolls(Stack<Integer> values) {
        this.values = values;
    }

    public static Dolls fromColumn(int col, int[][] board) {
        Stack<Integer> values = new Stack<>();
        for (int row = board.length-1; row >= 0; row--) {
            if(board[row][col] == 0){
                break;
            }
            values.push(board[row][col]);
        }
        return new Dolls(values);
    }

    public static Dolls empty() {
        return new Dolls(new Stack<>());
    }

    public boolean isEqualToPreviousDoll(int doll){
        if(values.empty()){
            return false;
        }
        return values.peek().equals(doll);
    }

    public boolean isEmpty(){
        return values.empty();
    }

    public int removeDoll(){
        if (values.empty()) {
            throw new RuntimeException();
        }
        //비정상적인 상황이면 터트리는 게 낫다
        //디버깅 시에 수월한
        //isEmpty()를 활용해서 예외상황 모두 핸들하기
        return values.pop();
    }

    public void add(int doll) {
        values.push(doll);
    }
}
