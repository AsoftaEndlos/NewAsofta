package com.endlos.admin.user.Repository;

import com.endlos.admin.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    Boolean existsByUsername(String username);

    //public void save(User user, MultipartFile file);
    Boolean existsByEmail(String email);

    //	@Query("SELECT u FROM User u WHERE u.status = ?1")
//	List<User> findBystatus(@Param("status") Integer status );
    //	Page<User> findBystatus(@Param("status") Integer status ,Pageable pageable);
    //Page<User> FindAll(Long id,org.springframework.data.domain.Pageable pageble);
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByUser(@Param("email") String email);

}
