/**
 * Provides the classes to represent a Command service
 * where all logic for excecution of commands are here
 *
 * @since 0.0.4
 */
package com.sn0wf1ake.backend.utils;

import java.util.ArrayList;

public class SFStringUtils {

    // Private constructor to prevent instantiation
    private SFStringUtils() {
        throw new UnsupportedOperationException("String Utility class");
    }

    // Retrieve the first arg and the rest
    public static SFPair<String, String> retrieveFirstArg(String args) {

        if(args == null) return null;

        // Remove all leading spaces
        // @see https://www.w3schools.com/java/ref_string_replacefirst.asp
        String argsNoLeadingSpace = args.replaceFirst("^\\s+", "");

        int spaceIndex = argsNoLeadingSpace.indexOf(" ");

        if(spaceIndex == -1) return new SFPair<String, String>(argsNoLeadingSpace, "");

        String firstArg = argsNoLeadingSpace.substring(0, spaceIndex);
        String remainArgs = argsNoLeadingSpace.substring(spaceIndex).replaceFirst("^\\s+", "");

        return new SFPair<String, String>(firstArg, remainArgs);

    }

    // Split the args into an array list of arg
    public static ArrayList<String> retrieveAllArgs(String args) {

        if(args == null) return new ArrayList<>();
        
        String[] arr_args = args.split(" ");

        ArrayList<String> list_args = new ArrayList<>();

        for(String arg : arr_args) {
            list_args.add(arg);
        }

        return list_args;

    }

    // /**
    //  * Retrieve the first possible set of flags with validation, where it:
    //  * 1. if agmented by --flag, then return this flag and the rest of the args
    //  * 2. if agmented by -f, then return all possible leading flags and the rest of args
    //  */ 
    // public static SFPair<String, String> retrieveFirstFlag(String args, ArrayList<String> acc_short, ArrayList<String> acc_long) {
        
    // }

}