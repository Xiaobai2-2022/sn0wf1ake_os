/**
 * Provides the classes necessary to communicate with
 * the frontend for the command parser.
 *
 * @since 0.0.1 
 */
package com.sn0wf1ake.backend.controller;

import com.sn0wf1ake.backend.model.CommandModel;
import com.sn0wf1ake.backend.service.CommandService;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @PostMapping("/commands")
    public ResponseEntity<?> handleCommand(@RequestBody CommandModel commandModel) {

        try {
            Object result = commandService.executeCommand(commandModel);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
        
    }

}