// singleton bean example
package CGI.springframework.dependencyinjection.component;

import org.springframework.stereotype.Component;

@Component
public class Computer {

    private String screen;
    private String ram;
    private String processor;

    public Computer() {
        System.out.println("computer object generated");
    }

    public String computerMethod(){
        System.out.println("computer Method executed");
        return "computerMethod";
    }
}
