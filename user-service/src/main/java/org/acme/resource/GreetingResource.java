package org.acme.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/updated")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloUpdated() {
        System.out.println("Here2");
        return "Modified REST";
    }

    @GET
    @Path("/updated2")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloUpdated2() {
        return "Modified REST";
    }

}
