package task.beautifulcode;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.function.Supplier;

public class JsonResponse {
    private final Supplier<String> jsonValue;

    public JsonResponse(Supplier<String> jsonValue) {
        this.jsonValue = jsonValue;
    }

    public void send(ServletResponse resp) throws IOException {
        String jsonValue = this.jsonValue.get();
        if (!jsonValue.isEmpty()) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(jsonValue);
        }
    }
}
