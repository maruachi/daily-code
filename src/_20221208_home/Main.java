package _20221208_home;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/92334?language=java
public class Main {
    public static void main(String[] args) {
        String[] idList = new String[]{
                "muzi",
                "apeach",
                "frodo",
                "muzi",
                "apeach"
        };

        String[] report = new String[]{
                "frodo",
                "frodo",
                "neo",
                "neo",
                "muzi"
        };

        int k = 2;

        HashMap<String, Set<String>> reporteeGroups = new HashMap<>();
        HashMap<String, Integer> reportCountMapper = new HashMap<>();
        int numUser = idList.length;
        for (int i = 0; i < numUser; i++) {
            String reporter = idList[i];
            String reportee = report[i];

            if (!reportCountMapper.containsKey(reportee)) {
                reportCountMapper.put(reportee, 0);
            }

            if (!reporteeGroups.containsKey(reporter)) {
                Set<String> newReportGroup = new HashSet<>();
                newReportGroup.add(reportee);
                reporteeGroups.put(reporter, newReportGroup);
                reportCountMapper.put(reportee, reportCountMapper.get(reportee) + 1);
                continue;
            }

            Set<String> reporteeGroup = reporteeGroups.get(reporter);

            if (!reporteeGroup.contains(reportee)) {
                reporteeGroup.add(reportee);
                reportCountMapper.put(reportee, reportCountMapper.get(reportee) + 1);
            }
        }

        List<String> stopMembers = new ArrayList<>();
        for (String reportee: reportCountMapper.keySet()){
            if (reportCountMapper.get(reportee) >= k) {
                stopMembers.add(reportee);
            }
        }
        System.out.println(stopMembers);
        System.out.println(reporteeGroups);
        System.out.println(reportCountMapper);

        HashMap<String, Integer> mailCounts = new HashMap<>();
        for (String reporter : reporteeGroups.keySet()) {
            Set<String> reporteeGroup = reporteeGroups.get(reporter);
            int count = 0;
            for (String stopMember : stopMembers) {
                if (reporteeGroup.contains(stopMember)) {
                    count++;
                }
            }
            mailCounts.put(reporter, count);
        }
        System.out.println(mailCounts);
    }
}