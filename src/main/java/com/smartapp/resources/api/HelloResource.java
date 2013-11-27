package com.smartapp.resources.api;

import com.smartapp.openid.CustomUserDetails;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Hello resource
 *
 * Author: Nguyen Tan Nguyen <ntnguyen2603 at gmail dot com>
 */
@Component
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    @GET
    @Path("/{name}")
    public Response hello(@PathParam("name") String name){
        return Response.ok("Hello "+ name).build();
    }

    @POST
    public Response hello(CustomUserDetails customUserDetails){
        return Response.ok(customUserDetails).build();
    }
}
