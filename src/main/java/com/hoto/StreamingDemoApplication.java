package com.hoto;

import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.ServerProperties;

public class StreamingDemoApplication extends Application<StreamingDemoConfiguration> {

    public static void main(String... args) throws Exception {
        new StreamingDemoApplication().run("server", "config.yml");
    }

    @Override
    public String getName() {
        return "Streaming with JAX-RS";
    }

    @Override
    public void initialize(Bootstrap<StreamingDemoConfiguration> bootstrap) {
    }

    @Override
    public void run(StreamingDemoConfiguration configuration,
                    Environment environment) {
        var streamingResource = new StreamingResource(
            new LineDropper(),
            new JsonStreamer(),
            new LineStreamer());

        JerseyEnvironment jersey = environment.jersey();
        jersey.register(streamingResource);

        /*
         * If set to zero will essentially turn off the jersey buffer.
         * Zero value will "force" jersey to flush the content every time we call
         * `OutputStream.flush()` no matter how small the buffer is at that time.
         *
         * Also notice that the `Content-Length` disappears from the response
         * to the client if the response is larger then this buffer size.
         */
        jersey.property(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 0);
//        jersey.property(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 100);
//        jersey.property(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 1000);
//        jersey.property(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 8192); //default value
    }

}
