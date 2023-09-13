package com.jyusun.origin.base.launch.jackson.core.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Boolean转换
 *
 * @author jyusun at 10:45:14
 */
public class BooleanNumericSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeNumber(o ? 1 : 0);
    }

}
