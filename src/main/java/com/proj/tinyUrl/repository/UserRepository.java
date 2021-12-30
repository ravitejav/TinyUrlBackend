package com.proj.tinyUrl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.tinyUrl.entites.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
