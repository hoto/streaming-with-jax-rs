package com.hoto;

import java.io.OutputStream;

import static java.lang.String.format;

public class LineStreamer {

    /**
     *  Streams lines in form of:
     *
     *  line 1
     *  line 2
     *  line 3
     */
    public void stream(int items, OutputStream stream) {
        try {
            for (int i = 1; i <= items; i++) {
                stream.write(format("line %s\n", i).getBytes());
                stream.flush();
                Thread.sleep(100);
            }
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
