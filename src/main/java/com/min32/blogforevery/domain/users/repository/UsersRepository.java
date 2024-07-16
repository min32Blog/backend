package com.min32.blogforevery.domain.users.repository;

import com.min32.blogforevery.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByEmail(String email);
}
