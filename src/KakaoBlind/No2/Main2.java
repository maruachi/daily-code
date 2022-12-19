package KakaoBlind.No2;

public class Main2 {
    public static void main(String[] args) {
        int n = 5;
        solution(n);
    }

    private static void solution(int n) {
        Stars stars = Stars.createOneStar();
        for(int i = 0; i < n; i++){
            String starsRow = stars.getStarsRow();
            System.out.println(starsRow);
            stars.incrementCount();
        }
    }
}
