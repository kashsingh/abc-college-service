package org.abc.data.repository;

import org.abc.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserById(Integer id);

}
