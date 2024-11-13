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

    @Override
    public Object execute(String args) {

        ArrayList<String> lFs = new ArrayList<>();

        lFs.add("help");
        lFs.add("version");

        SFPair<String, String> firstArg = SFStringUtils.retrieveFirstLongFlag(SFStringUtils.retrieveFirstArg(args), lFs);

        if(firstArg.getKey() != null) {

            if(firstArg.getKey().equals("help")) {
                
                // Fetch echo's discription
                Optional<CommandInfo> commandInfoOpt = commandInfoService.findByCommand("echo");

                if(commandInfoOpt.isPresent()) {
                    CommandInfo commandInfo = commandInfoOpt.get();
                    return "echo: " + commandInfo.getShortDescription();
                }

                return "An unexpected error has Occured!";
                
            }

            if(firstArg.getKey().equals("version")) {

                // Fetch echo's version
                Optional<CommandInfo> commandInfoOpt = commandInfoService.findByCommand("echo");

                if(commandInfoOpt.isPresent()) {
                    CommandInfo commandInfo = commandInfoOpt.get();
                    return commandInfo.getVer();
                }

                return "An unexpected error has Occured!";

            }

            return firstArg.getKey();
        }

        ArrayList<Character> sFs = new ArrayList<>();

        sFs.add('e');
        sFs.add('E');
        sFs.add('n');

        firstArg = SFStringUtils.retrieveFirstShortFlag(SFStringUtils.retrieveFirstArg(args), sFs);



        if(args == null) return "";
        return args;
    }

}
