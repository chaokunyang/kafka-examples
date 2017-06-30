package com.timeyang.avro;

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

/**
 * @author yangck
 */
public class AvroSchemaExample {

    public static void main(String[] args) {
        Schema userSchema = ReflectData.get().getSchema(User.class);
        System.out.println(userSchema);

        Schema groupSchema = ReflectData.get().getSchema(User.class);
        System.out.println(groupSchema);
    }

}
