package _20220924;

import java.util.HashMap;
import java.util.Map;

public class BadUser2 {
    public static void main(String[] args) {
        String[] winningUsers = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] badUsers = new String[]{"fr*d*", "abc1**"};

        System.out.println(solution(winningUsers, badUsers));
    }

    private static int solution(String[] winningUsers, String[] badUsers) {
        Map<String, Integer> countMatched = createCountMatched(badUsers);

        for (String winningUser : winningUsers) {
            String badUser = whoIsMatched(badUsers, winningUser);
            if (badUser != null) {
                countMatched.put(badUser, countMatched.get(badUser) + 1);
            }
        }

        System.out.println(countMatched);

        int totalCase = 1;
        for (int count : countMatched.values()) {
            if (count == 0) {
                continue;
            }
            totalCase *= count;
        }

        return totalCase;
    }

    private static String whoIsMatched(String[] badUsers, String winningUser) {
        for (String badUser : badUsers) {
            if (isMatched(badUser, winningUser)) {
                return badUser;
            }
        }
        return null;
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

    private static Map<String, Integer> createCountMatched(String[] badUsers) {
        Map<String, Integer> countMatched = new HashMap<>();
        for (String badUser : badUsers) {
            countMatched.put(badUser, 0);
        }

        return countMatched;
    }
}
