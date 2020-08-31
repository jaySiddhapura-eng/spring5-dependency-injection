package CGI.springframework.dependencyinjection.services;

import org.springframework.stereotype.Service;

@Service
public class PropertyInjectedService implements GreetingService{

    @Override
    public String sayGreeting() {
        return "Property injected service";
    }
}
