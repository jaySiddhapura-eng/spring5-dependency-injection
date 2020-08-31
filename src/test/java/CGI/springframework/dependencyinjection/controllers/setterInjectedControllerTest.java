package CGI.springframework.dependencyinjection.controllers;

import CGI.springframework.dependencyinjection.services.SetterInjectedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class setterInjectedControllerTest {

    SetterInjectedController controller;

    @BeforeEach
    void setUp() {
        controller = new SetterInjectedController(); // create the controller instance
        controller.setGreetingService(new SetterInjectedService());   // then access the setter method to provide service instance
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}