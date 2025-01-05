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

// import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component("FTP")
public class CommandFTP implements Command {

    @Override
    public APIResponse<?> execute(String args) {

        long seed = 0x0131f180;
        int len = Integer.parseInt(args);
        SFRandomUtils rnd = new SFRandomUtils(seed);

        String hexStr = rnd.genHexStr(len);
        hexStr += "\n" + rnd.genHexStr(len);
        
        return APIResponse.success(hexStr + "\n");
        
    }

}
