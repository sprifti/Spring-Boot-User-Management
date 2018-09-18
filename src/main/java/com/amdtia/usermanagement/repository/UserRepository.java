package com.amdtia.usermanagement.repository;

import com.amdtia.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long aLong);

    User findByUsername(String username);

    User findByEmail(String email);

}
