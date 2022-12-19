package _20220926;

import java.util.HashMap;
import java.util.Map;

public class TestEx {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put(null, 1);
        System.out.println(map.get(null));

//        map.put("hi", 1);
//        System.out.println(map.get("hi"));
//        System.out.println(map.get("hello"));
//        System.out.println(map.get(null));
    }
}
