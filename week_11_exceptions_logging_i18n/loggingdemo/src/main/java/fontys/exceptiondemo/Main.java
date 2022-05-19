/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.exceptiondemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hvd
 */
public class Main {
    
    // generated using logr
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        
        int x = 3;
        System.out.println("x = " + x);
        logger.log(Level.INFO, () -> "x = " + x);
        logger.log(Level.FINE, () -> "x = " + x);
        
        // geerated using own code template -> logv
        // logger.log(Level.FINE, () -> "x = " + x);
        
        Logger logger = Logger.getLogger(Main.class.getName());        
        
        //logger.setLevel(Level.INFO);
        
        logger.log(Level.INFO,
                () -> String.format("Hello %1$s args length= %2$d ", "world", args.length)
        );
        logger.log(Level.SEVERE,
                () -> "This is a serious logmessage");
        logger.log(Level.FINEST,
                () -> "This is the finest logmessage");

//        try {
//            var fileHandler = new FileHandler("demolog.txt");
//            fileHandler.setLevel(Level.FINEST);
//            logger.addHandler(fileHandler);
//
//        } catch (IOException | SecurityException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Log File can't be opened! No file logger attached", ex);
//        }
//        
//        logger.setUseParentHandlers(false);
//        logger.setLevel(Level.FINEST);  // But don't do this in production code

        
    }

}
