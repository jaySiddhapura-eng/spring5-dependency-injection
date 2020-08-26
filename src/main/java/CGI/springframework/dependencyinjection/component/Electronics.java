package CGI.springframework.dependencyinjection.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // by default singleton ben created from this class
public class Electronics {

    @Autowired
    private Computer smallComp;

    public String accessingComputerFromElectronics(){
        smallComp.computerMethod();
        return "electronics method executed";
    }
}
