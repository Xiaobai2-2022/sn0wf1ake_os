/**
 * Provides the interface to represent a Command 
 * service which another command can extend it's
 * logic for excecution of command
 *
 * @since 0.0.2
 */
package com.sn0wf1ake.backend.service.command;

public interface Command {
    public Object execute(String args);
}
