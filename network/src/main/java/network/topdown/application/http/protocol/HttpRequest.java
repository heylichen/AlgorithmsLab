package network.topdown.application.http.protocol;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class HttpRequest {
    private String method;
    private String url;
    private String httpVersion;
    private Map<String,String> headers;

    public String getUrlLine() {
        return method + " " + url + " " + httpVersion;
    }
}
