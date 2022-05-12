package nl.fontys.reflectiondemos;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;


/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, Throwable {
        
        
        // Three ways to get the class type...
        
        // 1 Ask instance for its class
        DemoClass dc = new DemoClass();
        
        Class<? extends DemoClass> aClass = dc.getClass();
        System.out.println("aClass = " + aClass);
        
        
        // 2 Use .class
        Class<?> dcType2 = DemoClass.class;
        System.out.println("dcType2 = " + dcType2);
        
        // 3 Based on String
        System.out.println("Please enter a class name: ");
        //Scanner scanner = new Scanner("nl.fontys.reflectiondemos.DemoClass");
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.nextLine();
        Class<?> icType = Class.forName(input);
        
        // The previous line could have thrown ClassNotFoundException... 
        System.out.println("The given class exists!");
        
        // Get all constructors of this type
        Constructor<?>[] constructors = icType.getConstructors();
        
        // Print each constructor
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        
        Constructor<?> ctr = constructors[0];
        Object instance = ctr.newInstance();
        
        DemoClass dci = (DemoClass)instance;
        System.out.println( dci.getName() );
        
        // Get default constructor
        Constructor<?> defConstructor = icType.getConstructor();
        Object instanceOfDefConstructor = defConstructor.newInstance();
        System.out.println( instanceOfDefConstructor );
        
        // Get parameterized constructor
        Constructor<?> pConstructor = icType.getConstructor( String.class );
        Object instancePConstructor = pConstructor.newInstance( "Lisa" );
        System.out.println( instancePConstructor );
        
        // Get a Method based on name and invoke it on a certain instance
        Method gnMethod = icType.getMethod("getName");
        System.out.println("The name is: " + gnMethod.invoke(instance));
        
        // sayHello method is static void method, so it prints, but doesn't return something
        Method s1Method = icType.getMethod("sayHello");
        s1Method.setAccessible(true);
        s1Method.invoke( null );
    
        // sayHello static method with parameter
        Method s2Method = icType.getMethod("sayHello", String.class);
        s2Method.invoke(null, "Ibrahim");
        
        // inspect modifiers of method (integer values documented in ConstantFieldValues for java.lang.reflect.modifier)
        int modifiers = s1Method.getModifiers();
        System.out.println("modifiers = " + modifiers);
        System.out.println("Modifiers binary: " + Integer.toBinaryString(modifiers));
        
        // Find all methods in a type and if it's decorated with an annotation
        for( Method m : icType.getDeclaredMethods() ){
            
            if ( m.isAnnotationPresent( MethodMarker.class )) {
                MethodMarker annotation = m.getAnnotation( MethodMarker.class);
                String description= annotation.description();
                System.out.println("Method descr is: " + description);
                m.setAccessible(true);
                m.invoke(instance);
            } 
        }      
    }

}
