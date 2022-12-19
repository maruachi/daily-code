package KakaoBlind2022.No1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Reporters {
    private final Map<User, Set<User>> values;

    private Reporters(Map<User, Set<User>> values) {
        this.values = values;
    }

    public static Reporters create(String[] names){
        Map<User, Set<User>> values = new HashMap<>();
        for(String name : names){
            values.put(new User(name), new HashSet<>());
        }
        return new Reporters(values);
    }

    public void report(User name1, User name2){

    }

    public boolean hasReportee(User reporter, User reportee){
        return false;
    }
}
