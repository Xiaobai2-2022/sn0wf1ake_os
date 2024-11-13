/**
 * Provides the class to encapsulate Command List
 * Logic
 *
 * @since 0.0.5
 */
package com.sn0wf1ake.backend.service;

import com.sn0wf1ake.backend.model.CommandInfo;
import com.sn0wf1ake.backend.repository.CommandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandInfoService {

    private final CommandRepository commandRepository;

    @Autowired
    public CommandInfoService(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    // Find all commands
    public List<CommandInfo> findAllCommands() {
        return commandRepository.findAll();
    }

    // Find command by name
    public Optional<CommandInfo> findByCommand(String command) {
        return commandRepository.findByCommand(command);
    }

    // Find command by ID
    public Optional<CommandInfo> findById(Integer id) {
        return commandRepository.findById(id);
    }

    // Create command
    public CommandInfo createCommand(CommandInfo commandList) {
        return commandRepository.save(commandList);
    }

    // Update command
    public CommandInfo updateCommand(CommandInfo commandList) {
        return commandRepository.save(commandList);
    }

    // Delete command
    public void deleteCommand(Integer id) {
        commandRepository.deleteById(id);
    }

}
