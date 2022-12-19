package _20221201;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpHeader {
    private final Map<String, String> value;

    public HttpHeader(Map<String, String> value) {
        if (value == null) {
            this.value = Collections.emptyMap();
            return;
        }
        this.value = value;
    }

    public static HttpHeader createEmpty() {
        return new HttpHeader(Collections.emptyMap());
    }

    public HttpHeader with(String headerLine) {
        String key = "";
        String value = "";
        Map<String, String> newValues = new HashMap<>(this.value);
        newValues.put(key, value);

        return new HttpHeader(newValues);
    }
}
