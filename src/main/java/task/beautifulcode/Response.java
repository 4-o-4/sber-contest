package task.beautifulcode;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.function.Supplier;

public class Response {
    private final Supplier<String> value;

    public Response(Supplier<String> value) {
        this.value = value;
    }

    public void send(ServletResponse resp) throws IOException {
        String value = this.value.get();
        resp.getWriter().println(value);
    }
}
