package com.hoto.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class StreamingResource {

    @Timed
    @GET
    @Path("/test")
    public Response test(@QueryParam("quantity") Optional<Integer> quantity) {
        return Response.ok(quantity.orElse(5)).build();
    }

}
