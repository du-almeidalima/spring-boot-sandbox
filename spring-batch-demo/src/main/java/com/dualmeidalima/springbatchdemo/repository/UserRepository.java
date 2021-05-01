package com.dualmeidalima.springbatchdemo.repository;

import com.dualmeidalima.springbatchdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
