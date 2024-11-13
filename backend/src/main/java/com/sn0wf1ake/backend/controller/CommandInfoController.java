/**
 * Provides the the class to represent the Command
 * Info Controller
 *
 * @since 0.0.5
 */
package com.sn0wf1ake.backend.controller;

import com.sn0wf1ake.backend.APIResponse;
import com.sn0wf1ake.backend.model.CommandInfo;
import com.sn0wf1ake.backend.service.CommandInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commands")
public class CommandInfoController {

    private final CommandInfoService commandInfoService;

    @Autowired
    public CommandInfoController(CommandInfoService commandInfoService) {
        this.commandInfoService = commandInfoService;
    }

    // Get all commands
    @GetMapping
    public ResponseEntity<APIResponse<List<CommandInfo>>> getAllCommands() {
        List<CommandInfo> commands = commandInfoService.findAllCommands();
        return ResponseEntity.ok(APIResponse.success(commands));
    }

    // Get command by name
    @GetMapping("/{command}")
    public ResponseEntity<APIResponse<CommandInfo>> getCommandByName(@PathVariable String command) {
        Optional<CommandInfo> commandInfo = commandInfoService.findByCommand(command);
        return commandInfo.map(value -> ResponseEntity.ok(APIResponse.success(value)))
                .orElseGet(() -> ResponseEntity.status(404)
                        .body(APIResponse.failure("Command not found")));
    }

    // Create new command
    @PostMapping
    public ResponseEntity<APIResponse<CommandInfo>> createCommand(@RequestBody CommandInfo commandInfo) {
        CommandInfo createdCommand = commandInfoService.createCommand(commandInfo);
        return ResponseEntity.status(201).body(APIResponse.success(createdCommand));
    }

    // Update command
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CommandInfo>> updateCommand(@PathVariable Integer id, @RequestBody CommandInfo commandInfo) {
        Optional<CommandInfo> existingCommand = commandInfoService.findById(id);
        if (existingCommand.isPresent()) {
            commandInfo.setId(id);
            CommandInfo updatedCommand = commandInfoService.updateCommand(commandInfo);
            return ResponseEntity.ok(APIResponse.success(updatedCommand));
        } else {
            return ResponseEntity.status(404).body(APIResponse.failure("Command not found"));
        }
    }

    // Delete command
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteCommand(@PathVariable Integer id) {
        commandInfoService.deleteCommand(id);
        return ResponseEntity.ok(APIResponse.success("Command deleted successfully"));
    }
}
