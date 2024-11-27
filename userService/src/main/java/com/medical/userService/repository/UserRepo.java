package com.medical.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.userService.entity.user.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
