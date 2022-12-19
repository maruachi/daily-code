package KakaoBlind.No2;

public class Stars {
    private int count;

    private Stars(int count) {
        if(count <= 0){
            throw new RuntimeException();
        }
        this.count = count;
    }

    public static Stars createOneStar(){
        return new Stars(1);
    }

    public void incrementCount(){
        count++;
    }

    public String getStarsRow(){
        StringBuilder starsRow = new StringBuilder();
        for(int i = 0; i < count; i++){
            starsRow.append('*');
        }
        return starsRow.toString();
    }
}
