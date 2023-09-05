package task.beautifulcode.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import task.beautifulcode.models.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckBracketsDataTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void isValidExample1() throws JsonProcessingException {
        String response = "true";
        String json = "{\"text\": \"Вчера я отправился в поход в лес ((это мое любимое место для отдыха)) вместе с друзьями.\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertEquals(
                String.format("{\n  \"isCorrect\" : %s\n}", response),
                new CheckBracketsData(data).get());
    }

    @Test
    void isValidExample2() throws JsonProcessingException {
        String response = "true";
        String json = "{\"text\": \"Вчера я отправился в поход в лес вместе с друзьями.\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertEquals(
                String.format("{\n  \"isCorrect\" : %s\n}", response),
                new CheckBracketsData(data).get());
    }

    @Test
    void isValidExample3() throws JsonProcessingException {
        String response = "true";
        String json = "{\"text\": \")(\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertEquals(
                String.format("{\n  \"isCorrect\" : %s\n}", response),
                new CheckBracketsData(data).get());
    }

    @Test
    void isValidExample4() throws JsonProcessingException {
        String response = "false";
        String json = "{\"text\": \"Вчера я отправился в поход в лес (   ) вместе с друзьями.\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertEquals(
                String.format("{\n  \"isCorrect\" : %s\n}", response),
                new CheckBracketsData(data).get());
    }

    @Test
    void isValidExample5() throws JsonProcessingException {
        String response = "false";
        String json = "{\"text\": \"Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями. ()\"}";
        Data data = MAPPER.readValue(json, Data.class);
        assertEquals(
                String.format("{\n  \"isCorrect\" : %s\n}", response),
                new CheckBracketsData(data).get());
    }
}