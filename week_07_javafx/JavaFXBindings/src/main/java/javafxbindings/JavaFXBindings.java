package javafxbindings;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author hvd
 */
public class JavaFXBindings {

    public static void main(String[] args) {
        
        int n1 = 1;
        int n2 = 2;
        
        int intSum = n1 + n2;
        
        System.out.println("intSum = " + intSum); // 3
        
        n1 = 2;
        
        System.out.println("intSum = " + intSum);  // 3 The value of sum has not changed!


        // Properties are Observable...
        IntegerProperty num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 = new SimpleIntegerProperty(2);
        
        NumberBinding sum = num1.add(num2); // The binding is created...
        
        System.out.println("Is binding valid? " + sum.isValid()); // FALSE
        // but not calculated yet... 
        
        System.out.println("sum = " + sum.getValue());  // 3
        // When getValue() is invoked... 
        // the value of the Properties num1 and num2 is retrieved,
        // the sum calculated. The binding is set to valid. 
        
        System.out.println("Is binding valid? " + sum.isValid()); // TRUE
        
        num1.set(2); // num1 sends ValueChange to observers, sum invalidates...
        
        System.out.println("Is binding valid? " + sum.isValid()); // FALSE
       
        System.out.println("sum = " + sum.getValue());  // 4
        // getValue() triggers the calculation since the sum is currently invalid. 
        // After calculation set to valid again. 
        
        System.out.println("Is binding valid? " + sum.isValid());  // TRUE
        
        System.out.println("sum = " + sum.getValue()); // 4 The sum is still valid, so just returned from cache
        
        System.out.println("Is binding valid? " + sum.isValid());  // ... and stays valid: TRUE
        
        
        // Example of chaining
        IntegerProperty num3 = new SimpleIntegerProperty(10);
        
        NumberBinding multiplication = sum.multiply(num3);  // The sum is observer of num1 and num2,
        // but the sum is also Observable for others..., multiplication in this case
        // multiplication is invalid at definition...
        
        System.out.println("multiplication = " + multiplication.getValue());  //... but gets calculated and activated here (40)
        
        num1.set(5); // ValueChange of num1 invalidates sum and invalidates multiplication transitively      

        System.out.println("multiplication = " + multiplication.getValue()); // multilpication is invalid...
        // and asks sum for new value, sum is invalid and asks num1 for new value, sum and multiplication are 
        // calculated and validated. (70)
        
        num1.set(5); // Although num1 is set again, the value does not change. Therefore no invalidation...       
        
        System.out.println("Is it still valid after setting same value? " + multiplication.isValid() ); // TRUE
          
        
    }
}
