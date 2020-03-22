package com.hoto;


import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.OutputStream;

public class JsonStreamer {

    public void stream(OutputStream stream) {
        JsonGenerator generator = Json.createGenerator(stream);
        generator.writeStartArray();
        for (int i = 0; i < 100; i++) {
            generator.writeStartObject();
            generator.write("key", i);
            generator.flush();
            generator.writeEnd();
            sleep();
        }
        generator.writeEnd();
        generator.close();
    }

    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
