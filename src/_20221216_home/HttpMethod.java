package _20221216_home;

public enum HttpMethod {
    GET("GET"), POST("POST");

    String value;

    HttpMethod(String method) {
        this.value = method;
    }
}
