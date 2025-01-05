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
    public static SFPair<Boolean, String> checkIncompleteCommand(String args) {

        if(args.equals("")) return new SFPair<Boolean,String>(true, args);

        // Pick seed using Our Visual Assistant Designer's Birthday and create Random Object
        final long SEED = 0x0131F180;
        final int LENGTH = 32;
        SFRandomUtils rnd = new SFRandomUtils(SEED);

        boolean isComplete = true;

        // We first replace the "\\" pair from the unprocessed string
        String backSlashPlaceholder;

        // Generate a placeholder which is not in the current args list
        do {
            backSlashPlaceholder = rnd.genHexStr(LENGTH);
        } while(args.contains(backSlashPlaceholder));

        // Replace
        args = args.replaceAll("\\\\\\\\", backSlashPlaceholder);

        // We then replace the "\"" pair from the unprocessed string
        String doubleQuotePlaceholder;

        // Generate a placeholder which is not in the current args list
        do {
            doubleQuotePlaceholder = rnd.genHexStr(LENGTH);
        } while(args.contains(doubleQuotePlaceholder));

        // Replace
        args = args.replaceAll("\\\\\\\"", doubleQuotePlaceholder);

        // Check if the number of double quote is even
        isComplete &= SFStringUtils.countCharInStr(args, '\"') % 2 == 0;

        // // Check if the string ends with a single "\"
        // isComplete &= args.charAt(args.length() - 1) == '\\';

        if(args.charAt(args.length() - 1) == '\\') {
            isComplete = false;
            args = args.substring(0, args.length() - 1);
        } else {
            args += "\n";
        }

        // Reset the Quotations and Backslashs
        args = args.replaceAll(doubleQuotePlaceholder, "\\\\\\\"");
        args = args.replaceAll(backSlashPlaceholder, "\\\\\\\\");
        
        // The compeleteness of a command here is determined by the number of double quote in the line
        return new SFPair<Boolean,String>(isComplete, args);

    }

    /*
     * Process to check if there is incomplete command
     *     return true and the execute command if command is incomplete, 
     *     false and null otherwise
     */
    public static SFPair<Boolean, APIResponse<?>> procIncompleteCommand(String command, String args) {

        SFPair<Boolean, String> incResult = checkIncompleteCommand(args);

        if(incResult.getKey()) {
            return new SFPair<Boolean, APIResponse<?>>(false, null);
        }

        return new SFPair<Boolean, APIResponse<?>>(true, 
            APIResponse.success("incomplete", command + " " + incResult.getValue())
        );

    }

    /** 
     * Process the argument with format
     *     return the processed argument
     */
    public static String procArgsFormat(String args, String specialBackSlashPlaceholder) {

        // Pick seed using Our Visual Assistant Designer's Birthday and create Random Object
        final long SEED = 0x0131F180;
        final int LENGTH = 32;
        SFRandomUtils rnd = new SFRandomUtils(SEED);

        // We first replace the "\\" pair from the unprocessed string
        String backSlashPlaceholder;

        // Generate a placeholder which is not in the current args list
        do {
            backSlashPlaceholder = rnd.genHexStr(LENGTH);
        } while(args.contains(backSlashPlaceholder) 
            || backSlashPlaceholder.equals(specialBackSlashPlaceholder));

        // We then replace the "\"" pair from the unprocessed string
        String doubleQuotePlaceholder;

        // Generate a placeholder which is not in the current args list
        do {
            doubleQuotePlaceholder = rnd.genHexStr(LENGTH);
        } while(args.contains(doubleQuotePlaceholder) 
            || doubleQuotePlaceholder.equals(backSlashPlaceholder)
            || doubleQuotePlaceholder.equals(specialBackSlashPlaceholder));

        // Replace

        args = args.replaceAll("\\\\\\\\", backSlashPlaceholder);
        args = args.replaceAll("\\\\\\\"", doubleQuotePlaceholder);

        // Now after the "\"" is replaced, we can split between """
        String[] argList = args.split("\"", -1);

        for(int i = 0; i < argList.length; i += 2) {

            String[] subParts = argList[i].split(" ", -1);

            argList[i] = "";

            // Remove extra spaces for not quoted strings
            for(int j = 0; j < subParts.length; ++j) {
                if(j != 0) argList[i] += " ";
                argList[i] += subParts[j];
            }

            // if(subParts[subParts.length - 1].equals("")) {
            //     argList[i] += " ";
            // }

            argList[i] = argList[i].replaceFirst("^\\s+", "");
            argList[i] = argList[i].replaceAll("\\\\", "");
            argList[i] = argList[i].replaceAll(backSlashPlaceholder, specialBackSlashPlaceholder);

        }

        args = "";

        for(int i = 0; i < argList.length; i++) {
            args += argList[i];
        }

        args = args.replaceAll(backSlashPlaceholder, "\\\\");
        args = args.replaceAll(doubleQuotePlaceholder, "\\\\\\\"");

        return args;

    }
    
}
