package task.beautifulcode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    @JsonProperty("text")
    private String str;

    @Override
    public String toString() {
        return this.str;
    }
}
