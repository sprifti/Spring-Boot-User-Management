package com.amdtia.usermanagement.repository;

import com.amdtia.usermanagement.model.Groups;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Groups,Long> {
    @Override
    Optional<Groups> findById(Long aLong);
}
