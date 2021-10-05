package com.endlos.admin.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.endlos.admin.user.model.ERole;
import com.endlos.admin.user.model.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(ERole name);
}
