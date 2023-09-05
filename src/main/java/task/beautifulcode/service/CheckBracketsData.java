package task.beautifulcode.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import task.beautifulcode.models.Data;

import java.util.function.Supplier;

public class CheckBracketsData implements Supplier<String> {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final Data data;
    @JsonProperty("isCorrect")
    private boolean isCorrect;

    public CheckBracketsData(Data data) {
        this.data = data;
        this.isCorrect = true;
    }

    @Override
    public String get() {
        try {
            checkBrackets();
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkBrackets() {
        char[] array = this.data.toString().toCharArray();
        int count = -1;
        for (char c : array) {
            if (c == '(') {
                count = 0;
            } else if (count == 0) {
                if (c == ')') {
                    this.isCorrect = false;
                    break;
                } else if (!Character.isSpaceChar(c)) {
                    count++;
                }
            }
        }
    }
}
