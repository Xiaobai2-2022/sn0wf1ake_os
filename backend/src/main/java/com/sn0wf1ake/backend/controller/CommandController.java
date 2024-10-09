/**
 * Provides the classes necessary to communicate with
 * the frontend for the command parser.
 *
 * @since 0.0.1 
 */
package com.sn0wf1ake.backend.controller;

import com.sn0wf1ake.backend.model.CommandModel;
import com.sn0wf1ake.backend.service.command.*;
import com.sn0wf1ake.backend.service.CommandFactory;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommandController {

    @Autowired
    private CommandFactory commandFactory;

    @PostMapping("/execute")
    public ResponseEntity<?> handleCommand(@RequestBody CommandModel commandModel) {

        try {
            Command command = commandFactory.getCommand(commandModel.getCommand());
            Object result = command.execute(commandModel.getArgs());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

}
