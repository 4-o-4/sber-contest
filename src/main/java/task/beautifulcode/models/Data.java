package task.beautifulcode.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    @JsonProperty("text")
    private String str;

    @Override
    public String toString() {
        return this.str;
    }
}
