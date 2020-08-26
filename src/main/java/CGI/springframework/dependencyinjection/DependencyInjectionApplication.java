package CGI.springframework.dependencyinjection;

import CGI.springframework.dependencyinjection.component.Computer;
import CGI.springframework.dependencyinjection.component.Cycle;
import CGI.springframework.dependencyinjection.component.Electronics;
import CGI.springframework.dependencyinjection.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);

		MyController myController = (MyController) context.getBean("myController");
		myController.sayHi();

		// give me the computer object/bean [singleton bean]
		Computer myComp = (Computer) context.getBean("computer");		// this will give constructor string
		//myComp.computerMethod();
		Computer myComp1 = (Computer) context.getBean("computer");		// construction does not happen because of singelton


		// give me a cycle object/ bean [prototype bean]
		Cycle myCycle = (Cycle) context.getBean("cycle");	// bean of cycle class constructed
		Cycle myCycle1 = (Cycle) context.getBean("cycle");	// another bean of cycle class constructed

		// autowired example
		Electronics el1 = (Electronics) context.getBean("electronics");
		el1.accessingComputerFromElectronics();
	}

}
