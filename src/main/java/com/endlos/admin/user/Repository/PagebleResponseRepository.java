package com.endlos.admin.user.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.endlos.admin.user.model.User;

public interface PagebleResponseRepository extends  JpaRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE u.status = ?1")
	Page<User> findBystatus(@Param("status") int  status ,org.springframework.data.domain.Pageable paging);
}
