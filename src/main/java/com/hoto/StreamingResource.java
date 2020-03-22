package com.hoto;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import static java.lang.String.format;

@Path("/stream")
public class StreamingResource {

    private final JsonStreamer jsonStreamer;
    private final LineStreamer lineStreamer;

    public StreamingResource(JsonStreamer jsonStreamer, LineStreamer lineStreamer) {
        this.jsonStreamer = jsonStreamer;
        this.lineStreamer = lineStreamer;
    }


    @GET
    @Path("/control")
    @Produces(MediaType.TEXT_PLAIN)
    public Response control(@QueryParam("items") int items) {
        String response = "";
        for (int i = 1; i <= items; i++) {
            response = format("line %s", i);
        }
        return Response.ok(response).build();
    }

    @Timed
    @GET
    @Path("/simple/lines")
    @Produces(MediaType.TEXT_PLAIN)
    public Response simpleLinesStream(@QueryParam("items") int items) {
        StreamingOutput so = output -> lineStreamer.simpleStream(output, items);
        return Response.ok(so).build();
    }

    @Timed
    @GET
    @Path("/advanced/lines")
    @Produces(MediaType.TEXT_PLAIN)
    public Response advancedLinesStream(@QueryParam("items") int items,
                                        @QueryParam("buffer") int chunkBufferSize,
                                        @QueryParam("sleep") int sleepInMs) {
        StreamingOutput so = output -> lineStreamer.advancedStream(output, items, chunkBufferSize, sleepInMs);
        return Response.ok(so).build();
    }

    @Timed
    @GET
    @Path("/simple/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response simpleJsonStream(@QueryParam("items") int items) {
        StreamingOutput so = output -> jsonStreamer.simpleStream(output, items);
        return Response.ok(so).build();
    }

    @Timed
    @GET
    @Path("/advanced/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response advancedJsonStream(@QueryParam("items") int items,
                                       @QueryParam("buffer") int chunkBufferSize,
                                       @QueryParam("sleep") int sleepInMs
    ) {
        StreamingOutput so = output -> jsonStreamer.advancedStream(output, items, chunkBufferSize, sleepInMs);
        return Response.ok(so).build();
    }

}
