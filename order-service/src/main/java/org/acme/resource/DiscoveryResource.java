package org.acme.resource;

import io.smallrye.stork.Stork;
import io.smallrye.stork.api.Service;
import io.smallrye.stork.api.ServiceInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/discovery")
public class DiscoveryResource {

    private final Stork stork;

    public DiscoveryResource() {
        this.stork = Stork.getInstance();  // Make sure Stork is initialized correctly
    }

    @GET
    public String discover() {
        try {
            // Fetch the Stork instance for service discovery
            Service service = stork.getService("user-service");

            if (service == null) {
                return "No service found for 'user-service'";
            }

            // Get the service instances
            Iterable<ServiceInstance> instances = (Iterable<ServiceInstance>) service.getInstances();

            if (instances == null || !instances.iterator().hasNext()) {
                return "No instances found for 'user-service'";
            }

            StringBuilder sb = new StringBuilder("Discovered instances:\n");
            for (ServiceInstance instance : instances) {
                sb.append(instance.getHost()).append(":").append(instance.getPort()).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error during service discovery: " + e.getMessage();
        }
    }
}
