package com.aws.audit.entity.converter;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonNodeConverter extends JacksonAttributeConverter<JsonNode> {
    public JsonNodeConverter() {
        super(JsonNode.class);
    }
}
