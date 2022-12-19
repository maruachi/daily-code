package Crane1;

public class Main3 {
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
        int removedCount = 0;
        Busket[] Buskets = initBuskets(board);
        Busket cranedDollBusket = Busket.createEmpty();

        for(int move : moves){
            //find the top doll
            int index = move - 1;
            if(Buskets[index].isEmpty()){
                continue;
            }

            Doll cranedDoll = Buskets[index].checkTop();
            if(!cranedDollBusket.isEmpty() && cranedDollBusket.checkTop().equals(cranedDoll)){
                cranedDollBusket.removeTop();
                removedCount += 2;
            }else {
                cranedDollBusket.pileUp(cranedDoll);
            }
            Buskets[index].removeTop();
        }

        return removedCount;
    }


    private static Busket[] initBuskets(int[][] board) {
        int N = board.length;

        Busket[] Buskets = new Busket[N];

        for(int i = 0; i < N; i++){
            Buskets[i] = Busket.createEmpty();
        }

        for(int col = 1; col <= N; col++){
            int indexCol = col - 1;
            for(int floor = 1; floor <= N; floor++){
                int indexFloor = N - floor;
                int dollId = board[indexFloor][indexCol];
                Doll doll = null;
                try{
                    doll = new Doll(dollId);
                }catch (RuntimeException re){
                    break;
                }

                Buskets[indexCol].pileUp(doll);
            }
        }
        return Buskets;
    }
}
