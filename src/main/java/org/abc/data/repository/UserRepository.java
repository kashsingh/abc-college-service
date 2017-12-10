package org.abc.data.repository;

import org.abc.data.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

    User findUserByEmail(String email);
}
