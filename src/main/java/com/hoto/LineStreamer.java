package com.hoto;

import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class LineStreamer {

    /**
     * Stream lines:
     * line 1
     * line 2
     * line 3
     */
    public void simpleStream(OutputStream stream, int items) {
        try {
            for (int i = 1; i <= items; i++) {
                stream.write(format("line %s\n", i).getBytes());
                stream.flush();
            }
            stream.close();
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
        try {
            for (int i = 1; i <= items; i++) {
                stream.write(format("line %s\n", i).getBytes());
                stream.flush();
                if (i % chunkBufferSize == 0) {
                    String sleeping = format("sleeping for %sms...\n", sleepInMs);
                    System.out.println(sleeping);
                    stream.write(sleeping.getBytes());
                    stream.flush();
                    TimeUnit.MILLISECONDS.sleep(sleepInMs);
                }
            }
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
