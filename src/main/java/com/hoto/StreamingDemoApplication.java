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
        return "Streaming REST API Demo";
    }

    @Override
    public void initialize(Bootstrap<StreamingDemoConfiguration> bootstrap) {
    }

    @Override
    public void run(StreamingDemoConfiguration configuration,
                    Environment environment) {
        StreamingResource streamingResource = new StreamingResource(new JsonStreamer());

        JerseyEnvironment jersey = environment.jersey();
        jersey.property(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 0);
        jersey.register(streamingResource);
    }

}
