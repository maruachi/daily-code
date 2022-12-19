package _20220924;

public class BadUser {
    public static void main(String[] args) {
        String[] winningUsers = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] badUsers = new String[]{"fr*d*", "abc1**"};

        System.out.println(solution(winningUsers, badUsers));

    }

    private static int solution(String[] winningUsers, String[] badUsers) {
        int totalCase = 1;
        for (String badUser : badUsers) {
            int count = 0;
            for (String winningUser : winningUsers) {
                if (isMatchedBadUser(badUser, winningUser)) {
                    count++;
                }
            }

            if (count != 0) {
                totalCase *= count;
            }
        }

        return totalCase;
    }

    private static boolean isMatchedBadUser(String badUser, String winningUser) {
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
}
