package _20221201;

public class HttpBeginLine {
    private final HttpMethod method;
    private final String url;
    private final String version;

    public HttpBeginLine(HttpMethod method, String url, String version) {
        if (method == null) {
            throw new RuntimeException();
        }

        if (url == null) {
            url = "/";
        }

        if (version == null) {
            throw new RuntimeException();
        }

        this.method = method;
        this.url = url;
        this.version = version;
    }

    public static HttpBeginLine parse(String beginLine) {
        int indexOfMethod = beginLine.indexOf(" ");
        if (indexOfMethod == -1) {
            throw new RuntimeException();
        }
        String method = beginLine.substring(0, indexOfMethod);

        String subLine = beginLine.substring(indexOfMethod).trim();
        int indexOfUrl = subLine.indexOf(" ");
        if (indexOfUrl == -1) {
            throw new RuntimeException();
        }
        String url = subLine.substring(0, indexOfUrl).trim();
        String version = subLine.substring(indexOfUrl).trim();

        return new HttpBeginLine(HttpMethod.convert(method), url, version);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }
}
