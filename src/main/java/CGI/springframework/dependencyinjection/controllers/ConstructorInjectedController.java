package CGI.springframework.dependencyinjection.controllers;

import CGI.springframework.dependencyinjection.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ConstructorInjectedController {


    private final GreetingService greetingService;


    @Autowired
    public ConstructorInjectedController(
            @Qualifier("constructorInjectedService")GreetingService greetingService)
    {
        this.greetingService = greetingService;
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}
