package CGI.springframework.dependencyinjection.services;

import org.springframework.stereotype.Service;

@Service
public class ConstructorInjectedService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Constructor Injected Service";
    }
}
