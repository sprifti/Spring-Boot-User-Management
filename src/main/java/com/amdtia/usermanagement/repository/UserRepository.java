package com.amdtia.usermanagement.repository;

import com.amdtia.usermanagement.model.Groups;
import com.amdtia.usermanagement.model.User;
import com.sun.xml.internal.bind.v2.TODO;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.WHERE;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long aLong);

    User findByUsername(String username);

    User findByEmail(String email);





}
