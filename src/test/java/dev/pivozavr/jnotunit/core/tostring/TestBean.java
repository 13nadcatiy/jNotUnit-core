package dev.pivozavr.jnotunit.core.tostring;

import dev.pivozavr.jnotunit.core.ObjectToJsonStringSerializable;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@Builder
public class TestBean implements ObjectToJsonStringSerializable {
    Integer id;
    String name;
    ArrayList<Integer> numbers;

    public String toString() {
        return toJsonString();
    }
}
