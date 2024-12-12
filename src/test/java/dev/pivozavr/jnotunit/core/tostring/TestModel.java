package dev.pivozavr.jnotunit.core.tostring;

import dev.pivozavr.jnotunit.core.ModelToJsonString;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class TestModel implements ModelToJsonString {

    Integer id;
    String name;
    ArrayList<Integer> numbers;

    public String toString() {
        return toJsonString();
    }
}