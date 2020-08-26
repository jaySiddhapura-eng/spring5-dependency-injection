package CGI.springframework.dependencyinjection.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Cycle {
    public Cycle() {
        System.out.println("cycle bean created");
    }
}
