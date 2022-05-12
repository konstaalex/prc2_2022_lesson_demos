package nl.fontys.reflectiondemos;

/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
public class DemoClass {
    
    private String name = "ALEX";

    // Default constructor
    public DemoClass() {
    }
    
    // Parameterized constructor
    public DemoClass( String name ) {
        this.name = name;
    }

    // Simple getter method returning name
    public String getName() {
        return name;
    }
    
    // A static method without parameters
    public static void sayHello(){
        System.out.println( "Hi!" );
    } 
    
    // A static method with parameters
    public static void sayHello(String to){
        System.out.println( "Hi " + to + "!");
    }

    // An annotated method
    @MethodMarker( description = "myAnnotatedMethod is described here")
    private void annotatedMethod(){
        System.out.println("You found me, I'm an annotated method.");
    }

    // The toString method
    @Override
    public String toString() {
        return "DemoClass{" + "name=" + name + '}';
    }
}
