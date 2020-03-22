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
        var streamingResource = new StreamingResource(new JsonStreamer(), new LineStreamer());

        JerseyEnvironment jersey = environment.jersey();
        jersey.register(streamingResource);

        /*
         * If set to 0 will turn off the jersey buffer.
         * 0 value will "force" jersey to flush the content every time we call `JsonGenerator.flush()`
         * no matter how "small" the buffer was at that time.
         *
         * For demo purposes only.
         */
        jersey.property(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 0);
    }

}
