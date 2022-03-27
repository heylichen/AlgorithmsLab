package network.topdown.application.http.protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseFirstLine {
    private String httpVersion;
    private String status;
    private String statusMsg;

    public String getLine() {
        return httpVersion + " " + status + " " + statusMsg;
    }
}
