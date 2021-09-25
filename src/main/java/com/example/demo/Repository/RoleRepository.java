package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ERole;
import com.example.demo.model.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(ERole name);
}
