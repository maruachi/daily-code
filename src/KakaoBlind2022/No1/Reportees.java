package KakaoBlind2022.No1;

import java.util.HashMap;
import java.util.Map;

public class Reportees {
    private final Map<User, Integer> values;

    private Reportees(Map<User, Integer> values) {
        this.values = values;
    }

    public static Reportees create(String[] names){
        Map<User, Integer> values = new HashMap<>();
        for(String name : names){
            values.put(new User(name), new Integer(0));
        }
        return new Reportees(values);
    }

    public void reportBy(User user){
        if(!values.containsKey(user)){
            throw new RuntimeException("There is no such user");
        }
        values.put(user, new Integer(values.get(user).intValue() + 1));
    }
}
