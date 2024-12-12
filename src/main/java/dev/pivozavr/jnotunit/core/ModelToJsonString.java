package dev.pivozavr.jnotunit.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public interface ModelToJsonString {

    private static <T> String toJsonStringMapper(T model) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(model);
        } catch (Exception e) {
            return model.toString();
        }
    }

    default String toJsonString() {
        return toJsonStringMapper(this).replace("\r\n", "\n");
    }
}