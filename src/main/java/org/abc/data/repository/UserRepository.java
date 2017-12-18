package org.abc.data.repository;

import org.abc.data.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);

}

