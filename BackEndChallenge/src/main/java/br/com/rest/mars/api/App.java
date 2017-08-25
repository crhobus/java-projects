package br.com.rest.mars.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author crhobus
 */
@ApplicationPath("rest")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        configure(resources);
        return resources;
    }

    private void configure(Set<Class<?>> resources) {
        resources.add(MarsAPI.class);
    }
}
