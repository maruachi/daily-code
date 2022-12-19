package KakaoBlind.No2;

public class Main {
    public static void main(String[] args) {
        int n = 3;

        solution(n);
    }

    private static void solution(int n) {
        for(int starNum = 1; starNum <= n; starNum++){
            printStars(starNum);
        }
    }

    private static void printStars(int n){
        for(int i = 0; i < n; i++){
            System.out.print('*');
        }
        System.out.println();
    }
}
