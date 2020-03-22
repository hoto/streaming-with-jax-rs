package com.hoto;

import com.hoto.resources.StreamingResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        environment.jersey().register(new StreamingResource());
    }

}
