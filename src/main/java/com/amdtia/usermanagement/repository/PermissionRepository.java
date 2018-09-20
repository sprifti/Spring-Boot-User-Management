package com.amdtia.usermanagement.repository;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permissions,Long> {

    @Override
    Optional<Permissions> findById(Long aLong);

    Permissions findByTitle(String title);
}
