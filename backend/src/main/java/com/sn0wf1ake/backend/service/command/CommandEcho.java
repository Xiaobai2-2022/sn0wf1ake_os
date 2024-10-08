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

import org.springframework.stereotype.Component;

@Component("echo")
public class CommandEcho implements Command {

    @Override
    public Object execute(String args) {
        if(args == null) return "\n";
        return args + "\n";
    }

}