/**
 * Provides the classes to process Command Requests
 *
 * @since 0.0.6
 */
package com.sn0wf1ake.backend.utils;

public class SFCommandUtils {

    // Private constructor to prevent instantiation
    private SFCommandUtils() {
        throw new UnsupportedOperationException("Command Utility class");
    }

    /*
     * Process to check if there is incomplete command
     *     return true if command is incomplete, false otherwise
     */
    public static boolean checkIncompleteCommand(String command) {
        
        // The compeleteness of a command here is determined by the number of double quote in the line
        return SFStringUtils.countCharInStr(command, '\"') % 2 == 0;

    }
    
}
