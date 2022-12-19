package _20220926;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BadUser2 {
    public static void main(String[] args) {
        String[] winningUsers = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] badUsers = new String[]{"fr*d*", "abc1**"};

        System.out.println(solution(winningUsers, badUsers));
    }

    private static int solution(String[] winningUsers, String[] badUsers) {
        Map<String, Integer> counter = createCounter(badUsers);

        for (String winningUser : winningUsers) {
//            operational 처리?? -> null이 나올 수 있다는 강조 표현!
            Optional<String> badUser = whoIsMatched(badUsers, winningUser);
//            if(!badUser.isPresent()){
//                continue;
//            }

//            badUser가 null인지 판단하는 것보다 badUser를 구하는 것과 아래의 코드를 독립적으로 작성
            if (counter.containsKey(badUser.get())) {
                counter.put(badUser.get(), counter.get(badUser.get()) + 1);
            }
        }

        System.out.println(counter);

        int totalCase = 1;
        for (int count : counter.values()) {
//            독립적으로 보장되어야 할 섹션의 개념으로 작성할 것
//            if (count == 0) {
//                continue;
//            }
//                totalCase *= count;
            if (count > 0) {
                totalCase *= count;
            }
        }

        return totalCase;
    }

    private static Optional<String> whoIsMatched(String[] badUsers, String winningUser) {
        for (String badUser : badUsers) {
            if (isMatched(badUser, winningUser)) {
                return Optional.of(badUser);
            }
        }

        return Optional.of("EMPTY");
    }

    private static boolean isMatched(String badUser, String winningUser) {
        if (badUser.length() != winningUser.length()) {
            return false;
        }

        for (int i = 0; i < winningUser.length(); i++) {
            if (badUser.charAt(i) == '*') {
                continue;
            }

            if (badUser.charAt(i) != winningUser.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private static Map<String, Integer> createCounter(String[] badUsers) {
        Map<String, Integer> countMatched = new HashMap<>();
        for (String badUser : badUsers) {
            countMatched.put(badUser, 0);
        }

        return countMatched;
    }
}
