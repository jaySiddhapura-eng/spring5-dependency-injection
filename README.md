# Dependency Injection

## SOLID Principle

1. **S**ingle Responsibility Principle
   1. Just because you can does not mean you should
   2. Every class should have one responsibility
   3. There should not be more than one reason for class to change
   4. Avoid "GOD" Class
   5. Split bigger classes into smaller classes
   6. Results into good test coverage
2. **O**pen / Close Principle
   1. Class should be open for extension but close for modification
   2. Use abstract base class
3. **L**iskov Substitution Principle
   1. If it looks like a duck, quack like a duck, but need battery, then you probably have wrong abstraction
   2. Object in program would be replaceable with instances of their subtype without altering the correctness of the program
4. **I**nterface Segregation Principle
   1. Make fine grain interface that are client specific
   2. Many client specific interfaces are better then one general purpose interface
5. **D**ependency Inversion Principle
   1. Avoid circular dependency
   2. Abstraction should not depends upon detail 
   3. But detail should depends upon abstraction
6. Focusing on Dependency management

## Spring Container

1. Spring container can be understand as a space created by spring framework

2. This space is being utilized by spring to store different objects 

3. This objects are known as **beans**

4. When spring project starts up, it **scans all the spring components** which are present in that particular project

5. After scanning all the components, it creates object(beans) of all the components

6. This created beans are stored in spring container

7. So whenever we ask for some object(bean) in our class for usage, spring provides us the exact bean which is already created by it and stored in spring container

8. Create a spring component name ```computer```

   ~~~java
   @Component
   public class Computer {
   
       // properties
       private String screen;
       private String ram;
       private String processor;
   
       // constructor
       public Computer() {
           System.out.println("computer object generated");
       }
   
       // method associated to this class
       public String computerMethod(){
           System.out.println("computer Method executed");
           return "computerMethod";
       }
   }
   ~~~

9. Now as soon as spring application starts up, it will create the instance of the computer class and keep this instance in the container, so whenever if some class need this instance it will provide it

10. Accessing the bean in application class

    ~~~java
    @SpringBootApplication
    public class DependencyInjectionApplication {
        ApplicationContext context = SpringApplication
            					   .run(DependencyInjectionApplication.class, args);
        
        Computer myComp = (Computer) context.getBean("computer");
        myComp.computerMethod();
    }
    ~~~

    ~~~java
    Output:
    computer Method executed
    ~~~

11. Point to be noted that, to obtain the instance of the computer class, we did not use ```new``` keyword

12. We simply obtained it from application context

13. Run method returns the application context, and we can access various context related method by using reference of application context

## Singelton Beans

1. In previous section we saw, how spring generates the object for us and store it in its container

2. We also noticed that the bean is created without any request by any other classes

3. When spring application starts up, this beans are created by the spring regardless of the further usage

4. This kind of beans are called singleton beans

5. All the beans are by default a singleton beans

6. To demonstrate create a component called ```computer```

   ~~~java
   @Component
   public class Computer {
   
       private String screen;
       private String ram;
       private String processor;
   
       // constructor will be executed whenever the instance from this class being created
       public Computer() {
           System.out.println("computer object generated");
       }
   }
   ~~~

7. Now we are not calling or creating any object from computer class, but spring scans the components and creates the beans out of this component, and since the bean is created from computer class, the constructor of computer class will be executed and we will get console output 

   ~~~java
   @SpringBootApplication
   public class DependencyInjectionApplication { 
   	public static void main(String[] args) {
   		SpringApplication.run(DependencyInjectionApplication.class, args);
   	}
   }
   
   ~~~

   ~~~java
   output: 
   computer object generated
   ~~~

8. **Singleton beans created regardless of its demand**

9. Singleton beans are created once, it means that two object from such class is not possible to create

10. To demonstrate this, we will try to create two different beans from computer class

    ~~~java
    @SpringBootApplication
    public class DependencyInjectionApplication {
    	public static void main(String[] args) { 
            
            // obtaining application context reference
        	ApplicationContext context = SpringApplication
                						.run(DependencyInjectionApplication.class, args);
            
            // accessing bean and try to give two different bean of computer class to the two 				different computer reference
            Computer myComp = (Computer) context.getBean("computer");
            Computer myComp1 = (Computer) context.getBean("computer");
        }
    }
    ~~~

11. So now technically the constructor of computer class should executed twice and we will get console output twice

12. But since the bean is singleton, the object is created once and the same object will be given to two different instance references ```myComp1``` and ```mComp2```

    ~~~java
    output:
    computer object generated
    ~~~

13. One singleton bean has two reference variable, ```myComp``` and ```myComp1``` are pointing to the exact same bean

14. Single Bean is shared and modifiable by all of its references 

## Prototype Beans

1. As we have seen in the singleton beans, the bean is created in the start up of the spring application, regardless of its demand, and there can not be more than one copy of this bean. The single copy of singleton bean is being used by all the references.

2. Prototype beans are created according to the need, fore example if ```computer``` bean is not needed in the app, then this bean will not be created at the first place. The only requirement is that, ```computer``` bean should be declared prototype bean.

3. Also multiple copy of prototype beans can be created, for example if some application need two different copy of the bean then spring container will create two different copy and will be given to two different references.

4. How to declare prototype beans, example : ```Cycle class```

   ~~~java
   @Component
   @Scope(value = "prototype")		// declare this class will be instantiated as prototype bean
   public class Cycle {
       
       // constructor will be called whenever bean is created 
       public Cycle() {
           System.out.println("cycle bean created");
       }
   }
   ~~~

5. Now lets see whether prototype bean is created by spring without any need

   ~~~java
   @SpringBootApplication
   public class DependencyInjectionApplication { 
   	public static void main(String[] args) { 
       	ApplicationContext context = SpringApplication
               					.run(DependencyInjectionApplication.class, args);
       }
   }
   ~~~

6. If we execute the above spring application, we will not get any console output which states that ```cycle bean created```, because This bean is not created automatically during spring application startup, unlike singleton bean.

7. Now lets see whether two different prototype beans are created on demand or not ?

8. Obtain the prototype beans in the application class

   ~~~java
   @SpringBootApplication
   public class DependencyInjectionApplication { 
   	public static void main(String[] args) { 
           
           // obtaining the reference of the spring context
       	ApplicationContext context = SpringApplication
               			.run(DependencyInjectionApplication.class, args);
           
           // bean of cycle class constructed
           Cycle myCycle = (Cycle) context.getBean("cycle");	
           // another bean of cycle class constructed
   	    Cycle myCycle1 = (Cycle) context.getBean("cycle");	
       }
   }
   ~~~

9. In above code snippet, we are obtaining prototype bean (```cycle```), twice and assign the two different references to this newly created two beans

10. Whenever ```getBean``` method executed to obtain the prototype beans, spring first creates the bean from the prototype class and then give this newly created bean to the reference, in our case ```myCycle``` and ```myCycle1```

## Possible way of Injecting the dependency [Non Spring]

1. There are three possible way of injecting the dependencies

   1. Injected at Property [not preferable]
   2. Injected at Constructor [best practice]
   3. Injected at Setter [Less preferable]

2. For demonstration purpose we will create a simple service : ```GreetingService``` interface

   ~~~java
   public interface GreetingService {
     String sayGreeting();
   }
   ~~~

3. And ```GreetingServiceImpl``` to implement the service logic

   ~~~java
   public class GreetingServiceImpl implements GreetingService {
       @Override
       public String sayGreeting() {
           return "greeting service hello";
       }
   }
   ~~~

4. We can access this service by creating its object and then calling its method on that object

5. **Injecting the service dependency using property**

6. Create a spring controller name ```PropertyInjectedController```

   ~~~java
   public class PropertyInjectedController {
       
       public GreetingService greetingService;
   
       public String getGreeting(){
           return greetingService.sayGreeting();
       }
   }
   ~~~

7. In above class as you can see, Greeting service can be accessed as a public property

8. To provide greeting service implementation, we have to access the public property of controller

9. Accessing the service in our test class 

   ~~~java
   class PropertyInjectedControllerTest {
       PropertyInjectedController controller;
   
       @BeforeEach
       void setUp() {
           // create new object of the controller
           controller = new PropertyInjectedController();
           // accessing the service proerty and create new service instance 
           controller.greetingService = new GreetingServiceImpl();
       }
   
       @Test
       void getGreeting(){
           // accessing the controller method
           System.out.println(controller.getGreeting());
       }
   }
   ~~~

10. **Injecting service dependency using Setter method**

11. Create the spring controller name ```SetterInjectedController```

    ~~~java
    public class SetterInjectedController {
        // property is private and to access this proerty you need setter method
        private GreetingService greetingService;
    
        // get the service instance using the setter method of controller class
        public void setGreetingService(GreetingService greetingService) {
            this.greetingService = greetingService;
        }
    
        public String getGreeting(){
            return greetingService.sayGreeting();
        }
    }
    ~~~

12. Injecting service dependency through the setter method of the controller in test class

    ~~~java
    class setterInjectedControllerTest {
    
        SetterInjectedController controller;
    
        @BeforeEach
        void setUp() {
            // create the controller instance
            controller = new SetterInjectedController(); 
            // then access the setter method to provide service instance
            controller.setGreetingService(new GreetingServiceImpl());   
        }
    
        @Test
        void getGreeting() {
            // accessing the controller method
            System.out.println(controller.getGreeting());
        }
    }
    ~~~

13. **Injecting service using Constructor of controller**

14. Create the spring controller name ```ConstructorInjectedController```

    ~~~java
    public class ConstructorInjectedController {
    
        private final GreetingService greetingService;
    
        // service dependency is provided as a param of the constructor
        public ConstructorInjectedController(GreetingService greetingService) {
            this.greetingService = greetingService;
        }
    
        public String getGreeting(){
            return greetingService.sayGreeting();
        }
    }
    ~~~

15. Injecting service dependency through constructor of the controller class in test class

    ~~~java
    class ConstructorInjectedControllerTest {
        
        ConstructorInjectedController controller;
        
        @BeforeEach
        void setUp() {
       // create controller instance using its constructor and provide service instance as a parameter
            controller = new ConstructorInjectedController(new GreetingServiceImpl());
        }
    
        @Test
        void getGreeting() {
            System.out.println(controller.getGreeting());
        }
    }
    ~~~

16. Goal: How we provide service instance to the controller in test class

17. The test classes are created using junit5

## Dependency Injection using Spring Framework

1. In order to inject the dependency through spring ```@Autowired``` annotation is being used

2. Same as explained in previous section, ```@Autowired``` annotation can apply to property, setter method or constructor

3. **Property injection** using ```@Autowired```

4. Service Modification

   ~~~java
   @Service
   public class GreetingServiceImpl implements GreetingService {
       @Override
       public String sayGreeting() {
           return "greeting service hello";
       }
   }
   ~~~

5. Note: Annotation must apply on implementation and not on interface

6. ```@Service``` annotation will make service a spring component

7. ```@Service, @Component, @Controller``` is used to make class a spring component

8. Modify the controller : ```PropertyInjectedController```

   ~~~java
   @Controller
   public class PropertyInjectedController {
   	
       // tell spring to take greeting service dependency from container
       @Autowired
       public GreetingService greetingService;
   
       public String getGreeting(){
           return greetingService.sayGreeting();
       }
   }
   ~~~

9. ```@Autowired``` annotation will inform spring to take the dependency from spring container

10. Note: ```@Autowired``` annotation will work on both public and private properties

11. Using controller in the application run method

    ~~~java
    @SpringBootApplication
    public class DependencyInjectionApplication { 
        public static void main(String[] args) {
            ApplicationContext context = SpringApplication
                					   .run(DependencyInjectionApplication.class, args);
            
          	System.out.println("-- property injected dependency");
            // getBean of the controller
    	    PropertyInjectedController pController = (PropertyInjectedController) 													context.getBean("propertyInjectedController");
            // using the method of controller
    		System.out.println(pController.getGreeting());
        }
    }
    ~~~

12. **Setter injection** using ```@Autowired```

13. Service will remain same now and change the controller : ```SetterInjectedController```

    ~~~java
    @Controller
    public class SetterInjectedController {
        private GreetingService greetingService;
    
        @Autowired	// on the setter method of greetingService property
        public void setGreetingService(GreetingService greetingService) {
            this.greetingService = greetingService;
        }
    
        public String getGreeting(){
            return greetingService.sayGreeting();
        }
    }
    ~~~

    ~~~java
    // main application
    @SpringBootApplication
    public class DependencyInjectionApplication { 
        public static void main(String[] args) {
            
            ApplicationContext context = SpringApplication
                					   .run(DependencyInjectionApplication.class, args);
            
           		System.out.println("-- setter injected dependency");
            
    	    	SetterInjectedController sController = (SetterInjectedController) 																context.getBean("setterInjectedController");
            
    			System.out.println(sController.getGreeting());
        }
    }
    ~~~

14. **Constructor injection** using ```@Autowired ``` [best practice]

15. Modify the controller : ```ConstructorInjectedController```

    ~~~java
    @Controller
    public class ConstructorInjectedController {
    
    
        private final GreetingService greetingService;
    	
        @Autowired	// on constructor
        public ConstructorInjectedController(GreetingService greetingService) {
            this.greetingService = greetingService;
        }
    
        public String getGreeting(){
            return greetingService.sayGreeting();
        }
    }
    ~~~

16. Using the controller in the main application class

    ~~~java
    @SpringBootApplication
    public class DependencyInjectionApplication { 
        public static void main(String[] args) {
            
            ApplicationContext context = SpringApplication
                					   .run(DependencyInjectionApplication.class, args);
    
            System.out.println("----constructor injected dependency");
    		ConstructorInjectedController cController = (ConstructorInjectedController) 												context.getBean("constructorInjectedController");
    		System.out.println(cController.getGreeting());
        }
    }
    ~~~

    

