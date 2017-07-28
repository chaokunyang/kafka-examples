package com.timeyang.kafka.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

/**
 * Avro Utils
 * @author chaokunyang
 */
class AvroUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Schema getSchema(Class<?> c) {
        Schema schema = ReflectData.get().getSchema(c);
        schema.getNamespace();
        return schema;
    }

    public static String getSchemaStr(Class<?> c) {
        Schema schema = ReflectData.get().getSchema(c);
        String schemaToRegister = schema.toString();

        try {
            schemaToRegister = objectMapper.writeValueAsString(schemaToRegister);
        } catch (JsonProcessingException e) {
            throw new SerializationException("json parse exception", e);
        }

        schemaToRegister = "{\"schema\": " + schemaToRegister + "}";

        return schemaToRegister;
    }

    public static String getSubject(Class<?> c) {
        return c.getCanonicalName();
    }

}
