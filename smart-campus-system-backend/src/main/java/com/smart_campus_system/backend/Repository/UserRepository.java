package com.smart_campus_system.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_campus_system.backend.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity ,Integer> {

}
