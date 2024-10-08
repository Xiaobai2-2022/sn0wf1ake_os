/**
 * Provides the classes to represent a Command service
 * where all logic for excecution of commands are here
 *
 * @since 0.0.1 
 */
package com.sn0wf1ake.backend.service;

import com.sn0wf1ake.backend.model.CommandModel;
import com.sn0wf1ake.backend.service.command.Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public Command getCommand(String commandName) {
        if(applicationContext.containsBean(commandName)) {
            return applicationContext.getBean(commandName, Command.class);
        }
        throw new IllegalArgumentException(commandName + ": Command not found\n");
    }

}
