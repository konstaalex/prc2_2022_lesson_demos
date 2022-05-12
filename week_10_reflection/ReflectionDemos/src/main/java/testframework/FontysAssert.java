package testframework;

/**
 *
 * @author hvd
 */
public class FontysAssert {
    
    public static void assertEquals(int actual, int expected){
        
        if( actual == expected ){
            System.out.println("GREEN TEST :-) ");
        } else {
            System.out.println("RED!!! Expected: " + expected + ", but actual value was: " + actual);
        }
        
    }
    
}
