package testframework;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Test executor class that executes all tests in test classes in the provided 
 * package. A "test" class is recognized by a class name containing "FontysTest"
 * @author hvd
 */
public class TestExecutor {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

        String packageName = "calculatortest";

        // Find all classes in the package that contain the String "FontysTest"
        Set<Class> testClasses = findAllTestClassesUsingClassLoader(packageName);
        
        if ( testClasses.isEmpty() ) {
            System.out.println("No test classes found! Class name should contain FontysTest");
            return;
        } else {
            System.out.println( testClasses );
        }

        for (Class testClass : testClasses) {

            // Find all methods in test class
            Method[] declaredMethods = testClass.getDeclaredMethods();

            // Filter the methods that are annotated with our FontysTestMethod annotation
            List<Method> testMethods = Arrays.stream(declaredMethods)
                    .filter(method -> method.isAnnotationPresent(FontysTestMethod.class))
                    .collect( Collectors.toList());

            if (testMethods.isEmpty()) {
                System.out.println("No test Methods found in test class " + testClass.getSimpleName());
                return;
            }

            for (Method testMethod : testMethods) {

                    // Create instance of test class
                    Constructor constructor = testClass.getConstructor();
                    Object testClassInstance = constructor.newInstance();

                    // Invoke test method on that instance
                    System.out.println("Test method: " + testMethod.getName());
                    testMethod.invoke(testClassInstance);
            }
        }
    }

    // derived from https://www.baeldung.com/java-find-all-classes-in-package
    public static Set<Class> findAllTestClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .filter(line -> line.contains("FontysTest"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    // derived from https://www.baeldung.com/java-find-all-classes-in-package
    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

}
