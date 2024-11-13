/**
 * Provides the classes to represent a CommandList Object
 * where the object represents a mySQL CommandList Object
 *
 * @since 0.0.5
 */
package com.sn0wf1ake.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "command_lists")
public class CommandInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String command;

    @Column(name = "short_description", nullable = false, columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "long_description", columnDefinition = "MEDIUMTEXT")
    private String longDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ver;

    // Constructors
    public CommandInfo() {
    }

    public CommandInfo(String command, String shortDescription, String longDescription, String ver) {
        this.command = command;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.ver = ver;
    }
    
    // Accessors and Mutators
    public Integer getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getVer() {
        return ver;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

}
