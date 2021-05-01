package com.dualmeidalima.springbatchdemo.repository;

import com.dualmeidalima.springbatchdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> { }
