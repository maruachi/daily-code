package ClickKeypad;

public class Main3 {
    public static void main(String[] args) {
        int[][] numbers = {{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
                           {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},
                           {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}};
        String[] hand = {"right", "left", "right"};

        for(int t = 0; t < numbers.length; t++){
            System.out.println(solution(numbers[t], hand[t]));
        }
    }

    private static String solution(int[] numbers, String hand) {
        StringBuilder stringBuilder = new StringBuilder();

        KeyFinder keyFinder = KeyFinder.create();
        Key leftHandKey = Key.createByChar('*');
        Key rightHandKey = Key.createByChar('#');

        for(int number : numbers){
            Side moveSide = null;
            Key key = Key.createByChar(Character.forDigit(number, 10));
            if(keyFinder.findSide(key) == Side.MIDDLE){
                int rdis = rightHandKey.distanceFrom(key);
                int ldis = leftHandKey.distanceFrom(key);
                if(hand == "right"){
                    moveSide = rdis <= ldis ? Side.RIGHT : Side.LEFT;
                }
                else{
                    moveSide = ldis <= rdis ? Side.LEFT : Side.RIGHT;
                }
            }

            if(moveSide == null){
                moveSide = keyFinder.findSide(key);
            }

            if(moveSide == Side.LEFT){
                leftHandKey = key;
                stringBuilder.append('L');
            }
            else if (moveSide == Side.RIGHT){
                rightHandKey = key;
                stringBuilder.append('R');
            }
        }

        return stringBuilder.toString();
    }
}
