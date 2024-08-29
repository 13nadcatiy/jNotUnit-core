package dev.pivozavr.jnotunit.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public interface ObjectToJsonStringSerializable {

    private static <T> String toJsonStringMapper(T object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return object.toString();
        }
    }

    default String toJsonString() {
        return toJsonStringMapper(this).replace("\r\n", "\n");
    }
}