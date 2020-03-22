package com.hoto;


import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JsonStreamer {

    /**
     * Stream a JSON array in form of:
     * [
     * {"item": 1},
     * {"item": 2},
     * {"item": 3}
     * ]
     */
    public void stream(OutputStream stream, int items) {
        JsonGenerator generator = Json
            .createGeneratorFactory(makeConfig())
            .createGenerator(stream, StandardCharsets.UTF_8);

        try {
            generator.writeStartArray(); // start array '['
            for (int i = 1; i <= items; i++) {
                generator.writeStartObject(); // start object '{'
                generator.write("item", i);
                generator.flush();
                generator.writeEnd(); // close object '}'
                Thread.sleep(10);
            }
            generator.writeEnd(); // close array ']'
            generator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pretty print json. For demo purpose only, don't use in production.
     */
    private Map<String, Object> makeConfig() {
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        return properties;
    }

}
