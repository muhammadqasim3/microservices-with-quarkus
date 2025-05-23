package org.acme.configuration;

import com.ecwid.consul.v1.ConsulClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ConsulConfig {

    @Produces
    public ConsulClient consulClient() {
        return new ConsulClient("localhost", 8500);
    }
}
