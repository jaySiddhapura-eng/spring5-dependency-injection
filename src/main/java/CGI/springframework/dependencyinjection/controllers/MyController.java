package CGI.springframework.dependencyinjection.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String sayHi(){
        System.out.println("hello world");
        return "hi folks";
    }
}
