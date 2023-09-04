package task.beautifulcode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonRequest<T> {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final ServletRequest req;

    public JsonRequest(ServletRequest req) {
        this.req = req;
    }

    public T get(Class<T> clazz) throws JsonProcessingException {
        String string = this.toString();
        return MAPPER.readValue(string, clazz);
    }

    @Override
    public String toString() {
        StringBuilder rtn = new StringBuilder();
        try (ServletInputStream inputStream = this.req.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String data = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                rtn.append(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rtn.toString();
    }
}
