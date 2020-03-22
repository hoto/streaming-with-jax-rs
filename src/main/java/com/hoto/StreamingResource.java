package com.hoto;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

@Path("/stream")
public class StreamingResource {

    private final JsonStreamer jsonStreamer;
    private final LineStreamer lineStreamer;

    public StreamingResource(JsonStreamer jsonStreamer, LineStreamer lineStreamer) {
        this.jsonStreamer = jsonStreamer;
        this.lineStreamer = lineStreamer;
    }

    @Timed
    @GET
    @Path("/lines")
    @Produces(MediaType.TEXT_PLAIN)
    public Response lines(@QueryParam("items") int sleepms,
                          @QueryParam("items") int buffer,
                          @QueryParam("items") int items) {
        StreamingOutput so = output -> lineStreamer.stream(sleepms, items, buffer, output);
        return Response.ok(so).build();
    }

    @Timed
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response json(@QueryParam("items") int items) {
        StreamingOutput so = output -> jsonStreamer.stream(items, output);
        return Response.ok(so).build();
    }

}
