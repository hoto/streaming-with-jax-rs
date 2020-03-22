package com.hoto;


import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class JsonStreamer {

    /**
     * Stream a JSON array in form of:
     * [
     * {"item": 1},
     * {"item": 2},
     * {"item": 3}
     * ]
     */
    public void simpleStream(OutputStream stream, int items) {
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
                TimeUnit.MILLISECONDS.sleep(100);
            }
            generator.writeEnd(); // close array ']'
            generator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Stream lines and sleep in between chunks:
     * line 1
     * line 2
     * sleeping for 1000ms...
     * line 3
     * line 4
     */
    public void advancedStream(OutputStream stream,
                               int items,
                               int chunkBufferSize,
                               int sleepInMs) {
        JsonGenerator generator = Json
            .createGeneratorFactory(makeConfig())
            .createGenerator(stream, StandardCharsets.UTF_8);

        try {
            generator.writeStartArray(); // start array '['
            for (int i = 1; i <= items; i++) {
                generator.writeStartObject(); // start object '{'
                generator.write("item", i);
                generator.writeEnd(); // close object '}'
                generator.flush();
                if (i % chunkBufferSize == 0) {
                    writeSleep(sleepInMs, generator);
                }
            }
            generator.writeEnd(); // close array ']'
            generator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeSleep(int sleepInMs, JsonGenerator generator) throws InterruptedException {
        String sleeping = format("sleeping for %sms...", sleepInMs);
        System.out.println(sleeping);
        generator.writeStartObject(); // start object '{'
        generator.write("item", sleeping);
        generator.writeEnd(); // close object '}'
        generator.flush();
        TimeUnit.MILLISECONDS.sleep(sleepInMs);
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
