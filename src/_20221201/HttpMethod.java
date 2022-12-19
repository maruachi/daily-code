package _20221201;

public enum HttpMethod {
    GET("GET"), POST("POST"), DELETE("DELETE"), PUT("PUT");

    private final String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public static HttpMethod convert(String value) {
        for (HttpMethod httpMethod : HttpMethod.values()) {
            if (httpMethod.value.equalsIgnoreCase(value)) {
                return httpMethod;
            }
        }
        throw new RuntimeException();
    }
}
