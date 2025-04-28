package com.example.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.Map;

public class JsonConverter implements AttributeConverter<Map<String, Object>, String> {


    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return json;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> resultMap = null;
        try {

            resultMap = objectMapper.readValue(dbData, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return resultMap;
    }


}
