/**
 * Provides the class to test functions
 * For Testing Purpose Only
 * 
 * Implements Command.java
 * 
 * @since 0.0.4
 */
package com.sn0wf1ake.backend.service.command;

import com.sn0wf1ake.backend.APIResponse;
import com.sn0wf1ake.backend.utils.*;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component("FTP")
public class CommandFTP implements Command {

    @Override
    public APIResponse<?> execute(String args) {

        SFPair<Boolean, APIResponse<?>> result = SFCommandUtils.procIncompleteCommand("FTP", args);

        if(result.getKey()) {
            return result.getValue();
        } else {
            // System.out.println(APIResponse.success(args + "\n"));
            return APIResponse.success(args + "\n");
        }
        
    }

}
