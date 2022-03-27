package network.topdown.application.http.protocol;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.util.Map;

@Getter
@Setter
public class HttpResponse {
    private ResponseFirstLine firstLine;
    private Map<String, String> headers;
    private ByteArrayOutputStream data;
}
