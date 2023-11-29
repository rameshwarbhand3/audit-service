package com.aws.audit.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public class JacksonAttributeConverter<T> implements AttributeConverter<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final Class<T> aclass;

    public JacksonAttributeConverter(Class<T> aclass) {
        this.aclass = aclass;
    }

    @Override
    public AttributeValue transformFrom(T input) {
        try {
            return AttributeValue.builder()
                    .s(mapper.writeValueAsString(input))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to serialize object", e);
        }
    }

    @Override
    public T transformTo(AttributeValue attributeValue) {
        try {
            return mapper.readValue(attributeValue.s(), this.aclass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to parse object", e);
        }
    }

    @Override
    public EnhancedType<T> type() {
        return EnhancedType.of(this.aclass);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.S;
    }
}
