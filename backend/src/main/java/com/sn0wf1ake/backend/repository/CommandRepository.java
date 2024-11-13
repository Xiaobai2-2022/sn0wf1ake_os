/**
 * Provides the interface to represent the Command
 * JPA Repository
 *
 * @since 0.0.5
 */
package com.sn0wf1ake.backend.repository;

import com.sn0wf1ake.backend.model.CommandInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandRepository extends JpaRepository<CommandInfo, Integer> {
    Optional<CommandInfo> findByCommand(String command);
}
