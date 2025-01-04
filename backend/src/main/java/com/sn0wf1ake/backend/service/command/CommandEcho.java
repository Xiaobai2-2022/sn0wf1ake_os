/**
 * Provides the class to represent a Command 
 * service which simulates the behaviour of
 * "echo" in linux
 * 
 * Implements Command.java
 * 
 * @since 0.0.2
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

@Component("echo")
public class CommandEcho implements Command {

    private final CommandInfoService commandInfoService;

    @Autowired
    public CommandEcho(CommandInfoService commandInfoService) {
        this.commandInfoService = commandInfoService;
    }

    private APIResponse<?> helpRoutine(String args) {
                
        // Fetch echo's discription
        Optional<CommandInfo> commandInfoOpt = commandInfoService.findByCommand("echo");

        if(commandInfoOpt.isPresent()) {
            CommandInfo commandInfo = commandInfoOpt.get();
            return APIResponse.success(commandInfo.getShortDescription() + '\n');
        }

        return APIResponse.failure("An unexpected error has Occured!");

    }

    private APIResponse<?> versionRoutine(String args) {

        // Fetch echo's version
        Optional<CommandInfo> commandInfoOpt = commandInfoService.findByCommand("echo");

        if(commandInfoOpt.isPresent()) {
            CommandInfo commandInfo = commandInfoOpt.get();
            return APIResponse.success(commandInfo.getVer() + '\n');
        }

        return APIResponse.failure("An unexpected error has Occured!");

    }

    private APIResponse<?> procShortFlag(String args, boolean eFlag, boolean nFlag) {

        args = SFStringUtils.processQuote(args);

        String out = "";

        if(eFlag) {
            args = args.replaceAll("\\\\b", "\b");
            args = args.replaceAll("\\\\t", "\t");
            args = args.replaceAll("\\\\n", "\n");
            args = args.replaceAll("\\\\f", "\f");
            args = args.replaceAll("\\\\r", "\r");
            args = args.replaceAll("\\\\\"", "\"");
            args = args.replaceAll("\\\\\'", "\'");
            // args = args.replaceAll("\\\\\\", "\\");
        }

        out += args;

        if(!nFlag) {
            out += "\n";
        }

        return APIResponse.success(out);

    }

    @Override
    public APIResponse<?> execute(String args) {



        // Check for command completeness
        SFPair<Boolean, APIResponse<?>> result = SFCommandUtils.procIncompleteCommand("echo", args);

        // For incomplete command, return sucess, incomplete
        if(result.getKey()) return result.getValue();

        // Check for long flags
        ArrayList<String> lFs = new ArrayList<>();

        lFs.add("help");
        lFs.add("version");

        SFPair<String, String> firstArg = SFStringUtils.retrieveFirstLongFlag(SFStringUtils.retrieveFirstArg(args), lFs);

        if(firstArg.getKey() != null) {

            if(firstArg.getKey().equals("help")) {
                return helpRoutine(args);
            }

            if(firstArg.getKey().equals("version")) {
                return versionRoutine(args);
            }

            return APIResponse.failure(firstArg.getKey());
            
        }

        // Check for short flags
        ArrayList<Character> sFs = new ArrayList<>();

        sFs.add('e');
        sFs.add('E');
        sFs.add('n');

        firstArg = SFStringUtils.retrieveFirstShortFlag(SFStringUtils.retrieveFirstArg(args), sFs);

        // Flags
        boolean eFlag = false;          // Check if the interpretation of backslash escapes is enabled
        boolean nFlag = false;          // Check if echoing trailing newline needs to be omited

        if(firstArg.getKey() != null)
            for(char c : firstArg.getKey().toCharArray()) {
                if(c == 'e') {
                    eFlag = true;
                } else if(c == 'E') {
                    eFlag = false;
                } else if(c == 'n') {
                    nFlag = true;
                } else {
                    return APIResponse.failure("An unexpected error has Occured!");
                }
            }

        return procShortFlag(firstArg.getValue(), eFlag, nFlag);

    }

}
