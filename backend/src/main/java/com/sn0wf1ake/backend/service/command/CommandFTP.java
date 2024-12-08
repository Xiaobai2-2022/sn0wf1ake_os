// /**
//  * Provides the class to test functions
//  * For Testing Purpose Only
//  * 
//  * Implements Command.java
//  * 
//  * @since 0.0.4
//  */
// package com.sn0wf1ake.backend.service.command;

// import com.sn0wf1ake.backend.utils.*;

// import java.util.ArrayList;
// import org.springframework.stereotype.Component;

// @Component("FTP")
// public class CommandFTP implements Command {

//     @Override
//     public Object execute(String args) {

//         // ArrayList<String> lFs = new ArrayList<>();

//         // lFs.add("help");
//         // lFs.add("version");

//         // SFPair<String, String> firstArg = SFStringUtils.retrieveFirstLongFlag(SFStringUtils.retrieveFirstArg(args), lFs);

//         ArrayList<Character> sFs = new ArrayList<>();

//         sFs.add('e');
//         sFs.add('E');
//         sFs.add('n');

//         SFPair<String, String> firstArg = SFStringUtils.retrieveFirstShortFlag(SFStringUtils.retrieveFirstArg(args), sFs);

//         // String ret = "Key: " + firstArg.getKey() + ", Value: " + firstArg.getValue() + ".";
        
//         return firstArg;
        
//     }

// }
