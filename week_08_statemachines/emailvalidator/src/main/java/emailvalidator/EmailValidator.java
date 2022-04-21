/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University of Applied Science Venlo
 */
package emailvalidator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// the idea is the regex is defined in a string ".+@(\\w+\\.)+(?<topdomain>\\w+)"
// you make a pattern out of it as in line 26
// you can feed the actual text to that pattern

/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
public class EmailValidator {

    //String regExString = ".+@.+";
    //String regExString = ".+@[0-9a-zA-Z]+\\.[0-9a-zA-Z]+";   // Make sure . at right side
    //String regExString = ".+@\\w+\\.\\w+";     // shorter -> word characters
    //String regExString = ".+@(\\w+\\.)+\\w+";    // enable more sub domains
    //String regExString = ".+@(\\w+\\.)+(\\w+)";    // group for top level domain
    
    String regExString = ".+@(\\w+\\.)+(?<topdomain>\\w+)";    // named group for top level domain
    Pattern regExPattern = Pattern.compile(regExString);

    /**
     * Check if given email address is valid. Should contain @ sign, at least
     * one character on the left-hand-side of it, at least one dot at the
     * right-hand-side (but the dot shouldn't be at begin or end of the
     * right-hand-side).
     *
     * @param emailAddress
     * @return
     */
    public boolean isValid(String emailAddress) {
        //return emailAddress.contains( "@");
        //Matcher matcher = regExPattern.matcher( emailAddress );
        //return matcher.matches();
        return emailAddress.matches(regExString);

    }

    /**
     * Get the top-level-domain. Returns right-most part of email address after
     * last dot. Assume that the email address is valid.
     *
     * @param emailAddress
     * @return top level domain name if found, otherwise null
     */
    public String getTopLevelDomain(String emailAddress) {
        Matcher matcher = regExPattern.matcher(emailAddress);
        if (matcher.matches()) {
            //int numberOfGroupsFound = matcher.groupCount();
            //return matcher.group( numberOfGroupsFound );
            return matcher.group("topdomain");

        } else {
            return null;
        }

    }

    public List<String> getValidAddresses(List<String> input) {

        //return input.stream().filter(regExPattern.asPredicate()).collect( Collectors.toList() );
        return input.stream().filter(s -> this.isValid(s)).collect(Collectors.toList());

        // Pattern p = Pattern.compile("\\d\\d\\d");
        // Matcher m = p.matcher("a123b");
        // System.out.println(m.find()); -> returns tru, because find looks for subsequences 
        // System.out.println(m.matches()); -> returns false, because matches checks only complete input
        // Matcher m = p.matcher("^a123b$"); -> now also find will result in false
    }

}
