package ClickKeypad4;

import ClickKeypad2.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String defalutHand = "right";
        //wrapping은 개념이 등장했을 때 하고 문제 처음에 풀 때는 단순하게 푼다.
        char leftCursor = '*';
        char rightCursor = '#';
        StringBuilder stringBuilder = new StringBuilder();

        //문제풀이시 순차적 사고 과정으로 접근해보기
        //순차적 사고 과정은 가장 단순한 문제 풀이해법에서 출발하고 그에 대한 필요에 의해 진행됨

        //문제에 있는 걸 가장 단순하게 풀기
        //targetCursor; position, key는 좋지 못한 식별명 단어는 하나로 가져가는 것이 깔끔함
        //기본 방향은 단어를 하나로 가져가고 싶다. 두 개 쓰는 건 어려워진다.
        // -> 이런 방향으로 생각했을 때 더 낳은 결과를 얻는지 확인
        for(char targetCursor : toCursors(numbers)){
            //targetCursor 3 가지 분류 1,4,7 -> leftCursor move
            //                       3,6,9 -> rightCursor move
            //                       2,5,8,0 -> 거리비교
            //쉬운 거 먼저 하는 게 이해하기가 쉽다. 어려운 건 뒤에다 배치
            if (isAlwaysCursorLeft(targetCursor)) {
                leftCursor = setLeft(stringBuilder, targetCursor);
                //setLeft는 개념단위로 묶음 targetCursor left side일 때 빌더에 L이 추가 그런데 이건 R도 똑같이 처리
                continue;
            }

            if (isAlwaysCursorRight(targetCursor)) {
                rightCursor = setRight(stringBuilder, targetCursor);
                continue;
            }
            // if문 끼리 완전 독립적인 코드가 되도록 해야한다.
            // 일단은 시간복잡도보다 순차적인 코드 작성하고 쉬운 코드를 얻는데 집중해보기
            // 시간복잡도는 추후에 개선

            CursorType cursorType = computeWhichCursorToMove(targetCursor, leftCursor, rightCursor);
            if(cursorType == CursorType.DEFAULT && defalutHand.equals("left")){
                leftCursor = setLeft(stringBuilder, targetCursor);
                continue;
            }

            if(cursorType == CursorType.DEFAULT && defalutHand.equals("right")){
                rightCursor = setRight(stringBuilder, targetCursor);
                continue;
            }

            if(cursorType == CursorType.LEFT){
                leftCursor = setLeft(stringBuilder, targetCursor);
                continue;
            }

            rightCursor = setRight(stringBuilder, targetCursor);

            // continue문이 많은 코딩 방식은 어색해보일지라도 개발자에게 아래의 코드에 유지보수가 필요함을 직관적으로 나타낼 수 있다
            // if문의 깊이가 2개 이상으로 깊어진다면 망한 코드라고 볼 수 있다.
            // middle을 처리하는 부분이 복잡하기에 유지보수가 일어날 여지가 많다.
            // 그렇기 때문에 유지보수가 많이 일어어나는 middle 부분의 로직을 객체지향으로 포함시켜야 한다는 니즈가 생긴다.
        }

        System.out.println(stringBuilder);
    }

    private static CursorType computeWhichCursorToMove(char targetCursor, char leftCursor, char rightCursor) {
        //int[] -> Position -> distance 계산
        //cursor는 position의 개념을 가진다
        Map<Character, int[]> cursorPositionMapper = createCursorPositionMapper();

//        int[] targetCursorPosition = cursorPositionMapper.get(targetCursor);
//        int[] leftCursorPosition = cursorPositionMapper.get(leftCursor);
//        int[] rightCursorPosition = cursorPositionMapper.get(rightCursor);
//        이렇게 모아 놓은 코드는 좋지가 않다. 중간에 처리 단계가 있기 때문인데 처리에 필요한 것만 만들어놓고 사용한다.

        int[] targetCursorPosition = cursorPositionMapper.get(targetCursor);
        int[] leftCursorPosition = cursorPositionMapper.get(leftCursor);

        int distanceFromLeftCursor = computeDistanceBetween(targetCursorPosition, leftCursorPosition);

        int[] rightCursorPosition = cursorPositionMapper.get(rightCursor);

        int distanceFromRightCursor = computeDistanceBetween(targetCursorPosition, rightCursorPosition);

        if(distanceFromLeftCursor == distanceFromRightCursor){
            return CursorType.DEFAULT;
        }

        if(distanceFromLeftCursor < distanceFromRightCursor){
            return CursorType.LEFT;
        }

        return CursorType.RIGHT;
    }

    private static int computeDistanceBetween(int[] targetCursorPosition, int[] currentCursorPosition) {
        return Math.abs(targetCursorPosition[0] - currentCursorPosition[0])
                + Math.abs(targetCursorPosition[1] - currentCursorPosition[1]);
    }

    private static Map<Character, int[]> createCursorPositionMapper() {
        Map<Character, int[]> cursorPositionMapper = new HashMap<>();
        cursorPositionMapper.put('1', new int[]{0,0});
        cursorPositionMapper.put('2', new int[]{0,1});
        cursorPositionMapper.put('3', new int[]{0,2});
        cursorPositionMapper.put('4', new int[]{1,0});
        cursorPositionMapper.put('5', new int[]{1,1});
        cursorPositionMapper.put('6', new int[]{1,2});
        cursorPositionMapper.put('7', new int[]{2,0});
        cursorPositionMapper.put('8', new int[]{2,1});
        cursorPositionMapper.put('9', new int[]{2,2});
        cursorPositionMapper.put('*', new int[]{3,0});
        cursorPositionMapper.put('0', new int[]{3,1});
        cursorPositionMapper.put('#', new int[]{3,2});
        return Collections.unmodifiableMap(cursorPositionMapper);
    }

    private static boolean isAlwaysCursorRight(char targetCursor) {
        //set도 좋지만 20~10개 이하일 때는 순차 검색이 빠르다
        return targetCursor == '3' || targetCursor == '6' || targetCursor == '9';
    }

    private static char[] toCursors(int[] numbers) {
        char[] cursors = new char[numbers.length];
        for (int i = 0; i <numbers.length; i++) {
            cursors[i] = (char) (numbers[i] + '0');
        }

        return cursors;
    }

    private static boolean isAlwaysCursorLeft(int targetCursor) {
        return targetCursor == '1' || targetCursor == '4' || targetCursor == '7';
    }

    private static char setLeft(StringBuilder builder, char targetCursor){
        builder.append('L');
        return targetCursor;
    }

    private static char setRight(StringBuilder builder, char targetCursor){
        builder.append('R');
        return targetCursor;
    }

    private enum CursorType{
        LEFT, RIGHT, DEFAULT

        //static 함수로 computeWhichCursorMove 가 가지는 게 자연스럽다.
    }
}
