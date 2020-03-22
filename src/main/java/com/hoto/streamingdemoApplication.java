package com.hoto;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class streamingdemoApplication extends Application<streamingdemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new streamingdemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "streamingdemo";
    }

    @Override
    public void initialize(final Bootstrap<streamingdemoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final streamingdemoConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
