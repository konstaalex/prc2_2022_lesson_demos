package calculatortest;

import static testframework.FontysAssert.assertEquals;
import testframework.FontysTestMethod;

/**
 *
 * @author hvd
 */
public class CalculatorFontysTest {
    
    // Play around by commenting out one or both annotations and see
    // that the framework does not execute test methods anymore.
    // If you would rename the class name to a name not containing
    // the text "FontysTest", the test class would not be found anymore. 
    
    @FontysTestMethod
    public void testCalculator_1(){
        
        Calculator calc = new Calculator();
        int expected = 10;
        int actual = calc.add(7, 3);
        
        assertEquals(actual, expected);
    }
    
    @FontysTestMethod
    public void testCalculator_2(){
        
        Calculator calc = new Calculator();
        int expected = -2;
        int actual = calc.add(3, -5);
        
        assertEquals(actual, expected);
    }
    
}
