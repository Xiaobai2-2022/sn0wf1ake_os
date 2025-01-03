/**
 * Provides the classes to process Command Requests
 *
 * @since 0.0.6
 */
package com.sn0wf1ake.backend.utils;

import com.sn0wf1ake.backend.APIResponse;

public class SFCommandUtils {

    // Private constructor to prevent instantiation
    private SFCommandUtils() {
        throw new UnsupportedOperationException("Command Utility class");
    }

    /*
     * Process to check if there is incomplete command
     *     return false if command is incomplete, true otherwise
     */
    public static boolean checkIncompleteCommand(String args) {
        
        // The compeleteness of a command here is determined by the number of double quote in the line
        return SFStringUtils.countCharInStr(args, '\"') % 2 != 0;

    }

    /*
     * Process to check if there is incomplete command
     *     return true and the execute command if command is incomplete, 
     *     false and null otherwise
     */
    public static SFPair<Boolean, APIResponse<?>> procIncompleteCommand(String command, String args) {

        if(!checkIncompleteCommand(args)) {
            return new SFPair<Boolean, APIResponse<?>>(false, null);
        }

        return new SFPair<Boolean, APIResponse<?>>(true, 
            APIResponse.success("incomplete", command + " " + args + "\n")
        );

    }
    
}
