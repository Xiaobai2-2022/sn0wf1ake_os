/**
 * Provides the classes to represent a Command object
 * as the model of which contains a command and args
 *
 * @since 0.0.1 
 */
package com.sn0wf1ake.backend.model;

public class CommandModel {

    private String command;
    private String args;

    // Default constructor for Spring to data bind
    public CommandModel() {}

    // Constructor for Object Creation
    public CommandModel(String command, String args) {
        this.command = command;
        this.args = args;
    }

    // Accessors and Mutators
    public String getCommand() {
        return this.command;
    }

    public String getArgs() {
        return this.args;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setArgs(String args) {
        this.args = args;
    }

}