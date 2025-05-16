package org.acme.configuration;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.dto.UserDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "user-service-api")
@Path("/users")  // Base path for the User Service APIs
public interface UserClientService {

    @GET
    @Path("/{id}")
    UserDTO getUserById(@PathParam("id") Long id);
}