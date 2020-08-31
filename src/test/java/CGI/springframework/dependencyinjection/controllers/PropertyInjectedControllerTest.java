package CGI.springframework.dependencyinjection.controllers;

import CGI.springframework.dependencyinjection.services.SetterInjectedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertyInjectedControllerTest {
    PropertyInjectedController controller;

    @BeforeEach
    void setUp() {
        controller = new PropertyInjectedController();
        controller.greetingService = new SetterInjectedService();
    }

    @Test
    void getGreeting(){
        System.out.println(controller.getGreeting());
    }
}