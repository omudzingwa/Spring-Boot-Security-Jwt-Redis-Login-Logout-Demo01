package com.quadrant.jwt.users;

import com.quadrant.jwt.userroles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
    Optional<User> findByUsername(String username);

    @Query(value = "select role from users where id = ?1",nativeQuery = true)
    public Role getUserRole(long id);
}
