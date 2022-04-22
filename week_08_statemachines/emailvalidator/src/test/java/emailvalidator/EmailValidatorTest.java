/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University of Applied Science Venlo
 */
package emailvalidator;

import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
public class EmailValidatorTest {

    static Stream<Arguments> emailDataProvider() {
        return Stream.of(
                // Message, email, valid    
                arguments( "Should be invalid, missing @", "r.vandenhamfontys.nl", false ),
                arguments( "Should be invalid, missing . in domain", "r.vandenham@nl", false ),
                arguments( "Should be invalid, ends with .", "r.vandenham@fontys.", false),
                arguments( "Should be invalid, ends with .", "r.vandenham@fontys.nl.", false),
                arguments( "Address should be valid", "r.vandenham@fontys.nl", true),
                arguments( "Address should be valid", "l.johnson@student.fontys.nl", true )
        );
    }

    //@Disabled("think TDD")
    @ParameterizedTest
    @MethodSource( "emailDataProvider" )
    void emailValid( String message, String email, boolean valid){ 
        
        EmailValidator validator = new EmailValidator();
        
        assertThat( validator.isValid( email ) )
                .as( message )
                .isEqualTo( valid );        

        //fail( "method emailValid reached end. You know what to do." );
    }
    
    //@Disabled("think TDD")
    @Test
    public void testTopLevelDomain() {
        
        EmailValidator validator = new EmailValidator();
        
        assertThat( validator.getTopLevelDomain( "R.vandenHam@fontys.nl") )
                .as( "Should be nl")
                .isEqualTo( "nl");
        
        //fail( "method testTopLevelDomain reached end. You know what to do." );
    }
    
    //@Disabled("think TDD")
    @Test
    public void testGetValidAddresses() {
        
        List<String> addresses = List.of(
                "R.vandenHam@fontys.nl", 
                "R.vandenHam@fontys.nl.", 
                "R.vandenHamfontys.nl", 
                "R.vandenHam@fontysnl");
        
        EmailValidator validator = new EmailValidator();
        System.out.println( validator.getValidAddresses( addresses ) );
        
        assertThat( validator.getValidAddresses( addresses ))
                .as( "Should be only one valid address")
                .containsExactly( "R.vandenHam@fontys.nl");
        
        
        //fail( "method testGetValidAddresses reached end. You know what to do." );
    }
    
}
