package KakaoBind2023;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class No1 {
    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = new String[]{};
        String[] privacies = new  String[]{};

        String string = Arrays.toString(solution(today, terms, privacies));
        System.out.println(string);
    }

    private static int[] solution(String today, String[] terms, String[] privacies) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate now = LocalDate.parse(today, dateTimeFormatter);

        Map<String, Integer> dueDateMapper = new HashMap<>();
        for (String term : terms) {
            StringTokenizer stringTokenizer = new StringTokenizer(term, " ");
            String termName = stringTokenizer.nextToken();
            int due = Integer.parseInt(stringTokenizer.nextToken());
            dueDateMapper.put(termName, due);
        }

        List<Integer> deleteNumbers = new ArrayList<>();
        int number = 1;
        for (String privacy : privacies) {
            StringTokenizer stringTokenizer = new StringTokenizer(privacy, " ");
            LocalDate startDate = LocalDate.parse(stringTokenizer.nextToken(), dateTimeFormatter);
            String termName = stringTokenizer.nextToken();
            int due = dueDateMapper.get(termName);
            LocalDate dueDate = startDate.plusMonths(due);

            if (now.isAfter(dueDate) || now.isEqual(dueDate)) {
                deleteNumbers.add(number);
            }
            number += 1;
        }

        int[] answer = new int[deleteNumbers.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = deleteNumbers.get(i);
        }

        return answer;
    }
}
