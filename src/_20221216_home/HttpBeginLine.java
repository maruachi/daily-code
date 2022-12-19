package _20221216_home;

import java.util.regex.Pattern;

public class HttpBeginLine {
    private final HttpMethod method;
    private final String url;
    private final String version;

    private HttpBeginLine(HttpMethod method, String url, String version) {
        if (method == null || url == null || version == null) {
            throw new RuntimeException("method: " + method + ", url: " + url);
        }

        if (!url.startsWith("/")) {
            throw new RuntimeException();
        }

        if (isInvalidHttpVersion(version)) {
            throw new RuntimeException();
        }

        this.method = method;
        this.url = url;
        this.version = version;
    }

    private boolean isInvalidHttpVersion(String version) {
        if (!version.toLowerCase().startsWith("http/")) {
            return false;
        }
        String versionNumber = version.substring("http/".length());

        int indexDot = versionNumber.indexOf('.');
        if (indexDot == -1) {
            return false;
        }
        String prefixNumber = versionNumber.substring(0, indexDot).trim();
        String suffixNumber = versionNumber.substring(indexDot+1).trim();

        if (isNotNumber(prefixNumber) || isNotNumber(suffixNumber)) {
            return false;
        }

        return true;
    }

    private boolean isNotNumber(String stringNumber) {
        if (stringNumber == null) {
            return false;
        }

        for (int i = 0; i < stringNumber.length(); i++) {
            char digit = stringNumber.charAt(i);
            if ('0' <=  digit && digit <= '9') {
                continue;
            }
            return false;
        }
        return true;
    }

    public static HttpBeginLine parseHttpBeginLine(String beginLine) {
        int indexMethod = beginLine.indexOf(' ');
        if (indexMethod == -1) {
            throw new RuntimeException();
        }
        String method = beginLine.substring(0, indexMethod);

//        HttpBeginLine httpBeginLine = new HttpBeginLine();
        return null;
    }
}
