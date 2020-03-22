package com.hoto;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class StreamingResource {

    private final JsonStreamer jsonStreamer;

    public StreamingResource(JsonStreamer jsonStreamer) {
        this.jsonStreamer = jsonStreamer;
    }

    @Timed
    @GET
    @Path("/stream")
    public Response test(@QueryParam("items") int items) {
        StreamingOutput so = output -> jsonStreamer.stream(items, output);
        return Response.ok(so).build();
    }

}
