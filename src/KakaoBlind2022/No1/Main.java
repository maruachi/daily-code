package KakaoBlind2022.No1;

//처리결과 -> 신고한 유저
//

import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String[] idList = new String[]{"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;

        System.out.println(solution(idList, report, k));
    }

    private static int[] solution(String[] idList, String[] report, int k) {
        Reporters reporters = Reporters.create(idList);
        Reportees reportees = Reportees.create(idList);


        //data passing 용 객체 만들어서 가공과 처리 분리하기
        for(String str : report){
            StringTokenizer tokenizer = new StringTokenizer(str, " ");
            User reporter = new User(tokenizer.nextToken());
            User reportee = new User(tokenizer.nextToken());
//            System.out.println(MessageFormat.format("name1: {0} name2: {1}", name1, name2));
            reporters.report(reporter, reportee);
            if(!reporters.hasReportee(reporter, reportee)){
                reportees.reportBy(reporter);
            }
        }

        //Reportee중에 정지 당한 사람들 추려서 reporter 가 몇명 가지고 있는지 확인하기

        return null;
    }
}
