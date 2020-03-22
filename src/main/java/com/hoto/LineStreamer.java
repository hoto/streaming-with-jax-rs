package com.hoto;

import java.io.OutputStream;

import static java.lang.String.format;

public class LineStreamer {

    /**
     *  Stream lines in form of:
     *
     *  line 1
     *  line 2
     *  line 3
     */
    public void stream(int sleepms, int buffer, int items, OutputStream stream) {
        try {
            for (int i = 1; i <= items; i++) {
                stream.write(format("line %s\n", i).getBytes());
                stream.flush();
                if (i % 1000 == 0) {
                    System.out.println("sleeping...");
                    Thread.sleep(1000);
                }
            }
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
