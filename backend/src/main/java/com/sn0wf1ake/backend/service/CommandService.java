/**
 * Provides the classes to represent a Command service
 * where all logic for excecution of commands are here
 *
 * @since 0.0.1 
 */

package com.sn0wf1ake.backend.service;

import com.sn0wf1ake.backend.model.CommandModel;

import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public Object executeCommand(CommandModel model) {
        switch (model.getCommand()) {

            case "echo":
                return command_echo(model.getArgs());
            default:
                throw new IllegalArgumentException(
                    model.getCommand() + ": command not found"
                );
        }
    }

    private String command_echo(String args) {

        if(args == null) return "\n";

        return args + "\n";

    }

}