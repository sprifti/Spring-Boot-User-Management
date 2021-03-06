package com.amdtia.usermanagement.repository;

import com.amdtia.usermanagement.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Groups,Long> {
    @Override
    Optional<Groups> findById(Long aLong);
     Groups findByGroupName(String groupName);

}
