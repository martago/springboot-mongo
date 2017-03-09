package de.janosch.springbootmongo;


import de.janosch.springbootmongo.rest.AddressREST;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(AddressREST.class);
    }

}