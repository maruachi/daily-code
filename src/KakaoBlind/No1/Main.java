package KakaoBlind.No1;

import java.util.Arrays;
import java.util.Stack;

public class Main {
    public final static int MAX_N = 3;

    public static void main(String[] args) {
        int[][] points = {{1,4}, {3,4}, {3,10}};

        int[] xs = new int[3];
        int[] ys = new int[3];
        for(int i = 0; i < MAX_N; i++){
            xs[i] = points[i][0];
            ys[i] = points[i][1];
        }
        System.out.println("xs : " + Arrays.toString(xs));
        System.out.println("ys : " + Arrays.toString(ys));

        for(int x : xs){

        }
    }
}
