package com.hoto;


import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.OutputStream;

public class JsonStreamer {

    public void stream(OutputStream stream) {
        JsonGenerator generator = Json.createGenerator(stream);
        generator.writeStartObject();
        for (int i = 0; i < 100; i++) {
            generator.write("key", i);
        }
        generator.writeEnd();
        generator.flush();
        generator.close();
    }
}
