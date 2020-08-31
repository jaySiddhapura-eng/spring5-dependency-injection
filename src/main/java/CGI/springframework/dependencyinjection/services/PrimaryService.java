package CGI.springframework.dependencyinjection.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class PrimaryService implements GreetingService{
    @Override
    public String sayGreeting() {
        return "from primary service ";
    }
}
