package com.tiker.repo;

import com.tiker.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for performing CRUD operations on configuration data.
 */
public interface ConfigRepository extends JpaRepository<ConfigEntity, Long> {
}
