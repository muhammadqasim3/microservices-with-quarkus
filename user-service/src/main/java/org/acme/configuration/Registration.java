package org.acme.configuration;

import com.ecwid.consul.v1.agent.model.NewService;
import io.quarkus.runtime.StartupEvent;
import com.ecwid.consul.v1.ConsulClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class Registration {

    @ConfigProperty(name = "quarkus.consul.agent.host")
    String consulHost;
    @ConfigProperty(name = "quarkus.consul.agent.port")
    String consulPort;
    @ConfigProperty(name = "service.id")
    String serviceId;
    @ConfigProperty(name = "service.name")
    String serviceName;
    @ConfigProperty(name = "service.host")
    String serviceHost;
    @ConfigProperty(name = "service.port")
    String servicePort;
    @ConfigProperty(name = "quarkus.consul.health-check-http-url")
    String healthCheckUrl;
    @ConfigProperty(name = "quarkus.consul.health-check-interval")
    String healthCheckInterval;
    @ConfigProperty(name = "quarkus.consul.health-check-timeout")
    String healthCheckTimeout;

    public void init(@Observes StartupEvent ev) {
        ConsulClient client = new ConsulClient(consulHost, Integer.parseInt(consulPort));
        NewService newService = new NewService();
        newService.setId(serviceId);
        newService.setName(serviceName);
        newService.setAddress(serviceHost);
        newService.setPort(Integer.valueOf(servicePort));
        NewService.Check check = new NewService.Check();
        check.setHttp(healthCheckUrl);
        check.setInterval(healthCheckInterval);
        check.setTimeout(healthCheckTimeout);
        newService.setCheck(check);
        client.agentServiceRegister(newService);
    }
}
