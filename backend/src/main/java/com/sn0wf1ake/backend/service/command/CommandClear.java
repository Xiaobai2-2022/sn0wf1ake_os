/**
 * Provides the class to represent a Command 
 * service which simulates the behaviour of
 * "clear" in linux
 * 
 * Implements Command.java
 * 
 * @since 0.0.5
 */
package com.sn0wf1ake.backend.service.command;

import com.sn0wf1ake.backend.APIResponse;
import com.sn0wf1ake.backend.model.CommandInfo;
import com.sn0wf1ake.backend.service.CommandInfoService;
import com.sn0wf1ake.backend.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component("clear")
public class CommandClear implements Command {

    private final CommandInfoService commandInfoService;

    @Autowired
    public CommandClear(CommandInfoService commandInfoService) {
        this.commandInfoService = commandInfoService;
    }

    @Override
    public APIResponse<?> execute(String args) {

        ArrayList<String> lFs = new ArrayList<>();

        lFs.add("help");
        lFs.add("version");

        SFPair<String, String> firstArg = SFStringUtils.retrieveFirstLongFlag(SFStringUtils.retrieveFirstArg(args), lFs);

        if(firstArg.getKey() != null) {

            if(firstArg.getKey().equals("help")) {
                
                // Fetch echo's discription
                Optional<CommandInfo> commandInfoOpt = commandInfoService.findByCommand("clear");

                if(commandInfoOpt.isPresent()) {
                    CommandInfo commandInfo = commandInfoOpt.get();
                    return APIResponse.success(commandInfo.getShortDescription() + '\n');
                }

                return APIResponse.failure("An unexpected error has Occured!");
                
            }

            if(firstArg.getKey().equals("version")) {

                // Fetch echo's version
                Optional<CommandInfo> commandInfoOpt = commandInfoService.findByCommand("clear");

                if(commandInfoOpt.isPresent()) {
                    CommandInfo commandInfo = commandInfoOpt.get();
                    return APIResponse.success(commandInfo.getVer() + '\n');
                }

                return APIResponse.failure("An unexpected error has Occured!");

            }

            return APIResponse.success(firstArg.getKey());
            
        }

        if(!args.equals("")) {
            return APIResponse.success("Usage: clear [ OPTION ]\nOptions: \n\t--version\t\tprint version\n\t--help\t\t\tprint help message\n");
        }

        return APIResponse.success("clear", "");

    }
    
}
