package CGI.springframework.dependencyinjection.services;

import org.springframework.stereotype.Service;

@Service
public class SetterInjectedService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Setter injected greeting service";
    }
}
