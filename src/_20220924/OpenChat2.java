package _20220924;

import java.text.MessageFormat;
import java.util.*;

public class OpenChat2 {
    public static void main(String[] args) {
        String[] records = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};

        System.out.println(solution(records));
    }

    private static List<String> solution(String[] records) {
        Map<String, String> userNameMapper = new HashMap<>();
        List<UserAccessLog> userAccessLogs = new ArrayList<>();
        for (String record : records) {
            StringTokenizer stringTokenizer = new StringTokenizer(record, " ");
            String command = stringTokenizer.nextToken();
            String userId = stringTokenizer.nextToken();

            if (stringTokenizer.hasMoreTokens()) {
                String name = stringTokenizer.nextToken();
                userNameMapper.put(userId, name);
            }

            userAccessLogs.add(new UserAccessLog(userId, command));
        }

        List<String> messages = new ArrayList<>();
        for (UserAccessLog userAccessLog : userAccessLogs) {
            messages.add(createMessage(userNameMapper, userAccessLog));
        }

        return messages;
    }

    private static String createMessage(Map<String, String> userNameMapper, UserAccessLog userAccessLog) {
        if (userAccessLog.getCommand().equals("Enter")) {
            return MessageFormat.format("{0}님이 들어왔습니다.", userNameMapper.get(userAccessLog.getUserId()));
        }

        if(userAccessLog.getCommand().equals("Leave")) {
            return MessageFormat.format("{0}님이 나갔습니다.", userNameMapper.get(userAccessLog.getUserId()));
        }

        return "적당한 메세지를 찾을 수 없습니다.";
    }

    private static class UserAccessLog {
        public final String userId;
        public final String command;

        public UserAccessLog(String userId, String command) {
            this.userId = userId;
            this.command = command;
        }

        @Override
        public String toString() {
            return "UserAccessLog{" +
                    "userId='" + userId + '\'' +
                    ", command='" + command + '\'' +
                    '}';
        }

        public String getUserId() {
            return userId;
        }

        public String getCommand() {
            return command;
        }
    }
}
