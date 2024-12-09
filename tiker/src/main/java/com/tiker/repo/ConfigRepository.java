package com.tiker.repo;

import com.tiker.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for performing CRUD operations on configuration data.
 */
public interface ConfigRepository extends JpaRepository<ConfigEntity, Long> {

    // Query to find the max ID (used to create a new config based on previous ID)
    @Query("SELECT MAX(c.id) FROM ConfigEntity c")
    Long findMaxId();
}
