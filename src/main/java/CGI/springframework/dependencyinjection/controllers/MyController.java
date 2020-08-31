package CGI.springframework.dependencyinjection.controllers;

import CGI.springframework.dependencyinjection.services.GreetingService;
import CGI.springframework.dependencyinjection.services.PrimaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private GreetingService greetingService;

    @Autowired
    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHi(){
        return this.greetingService.sayGreeting();
    }
}
