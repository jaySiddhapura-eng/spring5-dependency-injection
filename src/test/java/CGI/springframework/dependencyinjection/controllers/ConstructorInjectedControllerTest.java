package CGI.springframework.dependencyinjection.controllers;

import CGI.springframework.dependencyinjection.services.SetterInjectedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConstructorInjectedControllerTest {

    ConstructorInjectedController controller;
    @BeforeEach
    void setUp() {
        // create controller instance using its constructor and provide service instance as a parameter
        controller = new ConstructorInjectedController(new SetterInjectedService());
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}