package com.amdtia.usermanagement.repository;

import com.amdtia.usermanagement.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    @Override
    Optional<User> findById(Long aLong);
}
