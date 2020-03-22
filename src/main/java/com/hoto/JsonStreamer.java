package com.hoto;


import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.OutputStream;

public class JsonStreamer {

    /**
     *  Streams a JSON array in form of:
     *
     *  [
     *    {"item": 1},
     *    {"item": 2},
     *    {"item": 3}
     *  ]
     */
    public void stream(int items, OutputStream stream) {
        try {
            JsonGenerator generator = Json.createGenerator(stream);
            generator.writeStartArray(); // start array '['
            for (int i = 1; i <= items; i++) {
                generator.writeStartObject(); // start object '{'
                generator.write("item", i);
                generator.flush();
                generator.writeEnd(); // close object '}'
                Thread.sleep(100);
            }
            generator.writeEnd(); // close array ']'
            generator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
