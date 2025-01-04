/**
 * Provides the classes to process String
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
        
        String[] arrArgs = args.split(" ");

        ArrayList<String> listArgs = new ArrayList<>();

        for(String arg : arrArgs) {
            listArgs.add(arg);
        }

        return listArgs;

    }

    /**
     * Retrieve the first possible set of flags with validation, where it:
     * if agmented by --flag, then return this flag and the rest of the args
     */
    public static SFPair<String, String> retrieveFirstLongFlag(SFPair<String, String> retrieveFirst, ArrayList<String> accLong) {

        String firstArg = retrieveFirst.getKey();

        if(firstArg.indexOf("--") == 0) {
            
            String longFlag = firstArg.substring(2);
            if(accLong.contains(longFlag)) {
                return new SFPair<String, String>(longFlag, retrieveFirst.getValue());
            }

        }
        return new SFPair<String, String>(null, retrieveFirst.getKey() + " " + retrieveFirst.getValue());

    }

    /**
     * Retrieve the first possible set of flags with validation, where it:
     * if agmented by -f, then return all possible leading flags and the rest of args
     * 
     * Note: ArrayList only allow non-primitive type
     */
    public static SFPair<String, String> retrieveFirstShortFlag(SFPair<String, String> retrieveFirst, ArrayList<Character> accShort) {

        String firstArg = retrieveFirst.getKey();

        if(firstArg.indexOf("-") == 0) {
            
            String shortFlag = firstArg.substring(1);

            if(shortFlag == "") {
                return new SFPair<String, String>(null, retrieveFirst.getKey() + " " + retrieveFirst.getValue());
            }

            String shortFlags = "";

            for(char c : shortFlag.toCharArray()) {
                if(accShort.contains(c)) {
                    shortFlags = shortFlags + c;
                } else {
                    return new SFPair<String, String>(null, retrieveFirst.getKey() + " " + retrieveFirst.getValue());
                }
            }

            // Recursively call to retrive all next flags
            SFPair<String, String> retrieveNext = retrieveFirstArg(retrieveFirst.getValue());
            SFPair<String, String> shortFlagsNext = retrieveFirstShortFlag(retrieveNext, accShort);

            String shortFlagsSecond = shortFlagsNext.getKey();

            if(shortFlagsSecond != null) {
                shortFlags = shortFlags + shortFlagsNext.getKey();
                retrieveFirst.setValue(shortFlagsNext.getValue());
            }

            return new SFPair<String, String>(shortFlags, retrieveFirst.getValue());

        }
        
        return new SFPair<String, String>(null, retrieveFirst.getKey() + " " + retrieveFirst.getValue());

    }

    /**
     * Returns the number of appearance of given char c in given string str
     */
    public static int countCharInStr(String str, char c) {

        // ch -> ch == c is a lambda expression
        long count = str.chars().filter(ch -> ch == c).count();
        return (int) count;

    }

    /**
     * Process the string with quoted format
     * 
     * Note: ArrayList only allow non-primitive type
     */
    public static String processQuote(String str) {

        String[] allParts = str.split("\"", -1);

        for(int i = 0; i < allParts.length; i += 2) {

            System.out.println(allParts[i]);

            String[] subParts = allParts[i].split(" ");

            allParts[i] = "";

            // Remove extra spaces for not quoted strings
            for(int j = 0; j < subParts.length; j++) {
                if(j != 0) allParts[i] += " ";
                allParts[i] += subParts[j];
            }

            System.out.println(allParts[i]);

            allParts[i] = allParts[i].replaceFirst("^\\s+", "");

            // Remove all the escape sequences except for \\
            String placeholder = "__BACKSLASH_PLACEHOLDER_FX_Sn0wf1ake_DEV__";

            allParts[i] = allParts[i].replaceAll("\\\\\\\\", placeholder);
            allParts[i] = allParts[i].replaceAll("\\\\", "");
            allParts[i] = allParts[i].replaceAll(placeholder, "\\");

        }

        str = "";

        boolean requireSpace = false;

        for(int i = 0; i < allParts.length; i++) {
            if(allParts[i].equals("")) {
                requireSpace = false;
                continue;
            }
            if(requireSpace) str += " ";
            str += allParts[i];
            requireSpace = true;
        }

        return str;

    }



}
