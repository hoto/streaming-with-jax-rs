package com.hoto;


import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(DropwizardExtensionsSupport.class)
class StreamingResourceTest {

    private final LineDropper lineDropper = mock(LineDropper.class);
    private final LineStreamer lineStreamer = mock(LineStreamer.class);
    private final JsonStreamer jsonStreamer = mock(JsonStreamer.class);

    public ResourceExtension DROPWIZARD = ResourceExtension
        .builder()
        .addResource(new StreamingResource(lineDropper, jsonStreamer, lineStreamer))
        .build();

    @Test
    void hello() {
        assertThat(true).isTrue();
    }

}
