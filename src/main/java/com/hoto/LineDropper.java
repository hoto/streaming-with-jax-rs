package com.hoto;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class LineDropper {

    /**
     * No streaming, construct a String then return it whole.
     * For comparison purposes.
     */
    public String dropLines(int items) {
        StringBuilder response = new StringBuilder();
        try {
            for (int i = 1; i <= items; i++) {
                response.append(format("line %s\n", i));
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.toString();
    }
}
