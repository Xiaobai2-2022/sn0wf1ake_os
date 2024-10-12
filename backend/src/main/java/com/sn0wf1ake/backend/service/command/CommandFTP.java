/**
 * Provides the class to test functions
 * For Testing Purpose Only
 * 
 * Implements Command.java
 * 
 * @since 0.0.2
 */
package com.sn0wf1ake.backend.service.command;

import com.sn0wf1ake.backend.utils.*;

import org.springframework.stereotype.Component;

@Component("FTP")
public class CommandFTP implements Command {

    @Override
    public Object execute(String args) {

        SFPair<String, String> firstArg = SFStringUtils.retrieveFirstArg(args);

        String ret = "Key: \"" + firstArg.getKey() + "\", Value: \"" + firstArg.getValue() + "\".";
        
        return ret;
    }

}
