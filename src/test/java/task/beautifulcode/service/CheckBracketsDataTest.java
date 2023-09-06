package task.beautifulcode.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import task.beautifulcode.model.Data;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckBracketsDataTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void isValidExample1() throws JsonProcessingException {
        String json = "{\"text\": \"Вчера я отправился в поход в лес ((это мое любимое место для отдыха)) вместе с друзьями.\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertTrue(new CheckBracketsData(data).get().contains("true"));
    }

    @Test
    void isValidExample2() throws JsonProcessingException {
        String json = "{\"text\": \"Вчера я отправился в поход в лес вместе с друзьями.\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertTrue(new CheckBracketsData(data).get().contains("true"));
    }

    @Test
    void isValidExample3() throws JsonProcessingException {
        String json = "{\"text\": \")(\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertTrue(new CheckBracketsData(data).get().contains("true"));
    }

    @Test
    void isValidExample4() throws JsonProcessingException {
        String json = "{\"text\": \"Вчера я отправился в поход в лес (   ) вместе с друзьями.\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertFalse(new CheckBracketsData(data).get().contains("true"));
    }

    @Test
    void isValidExample5() throws JsonProcessingException {
        String json = "{\"text\": \"Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями. ()\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertFalse(new CheckBracketsData(data).get().contains("true"));
    }
}