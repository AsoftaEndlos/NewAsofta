package com.endlos.admin.user.Repository;

import com.endlos.admin.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagebleResponseRepository extends JpaRepository<User, Long> {
//	@Query("SELECT u FROM User u WHERE u.status = ?1")
//	Page<User> findBystatus(@Param("status") int  status ,org.springframework.data.domain.Pageable paging);
}
