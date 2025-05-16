package org.acme.config;

import com.ecwid.consul.v1.agent.model.NewService;
import io.quarkus.runtime.StartupEvent;
import com.ecwid.consul.v1.ConsulClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class Registration {
    public void init(@Observes StartupEvent ev) {
        ConsulClient client = new ConsulClient("localhost", 8500);
        NewService newService = new NewService();
        newService.setId("user-service-1");
        newService.setName("user-service");
        newService.setAddress("localhost");
        newService.setPort(8085);
        NewService.Check check = new NewService.Check();
        check.setHttp("http://localhost:8081/users");
        check.setInterval("10s");
        check.setTimeout("5s");
        newService.setCheck(check);
        client.agentServiceRegister(newService);
    }
}
