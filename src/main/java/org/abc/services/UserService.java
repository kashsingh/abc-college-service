package org.abc.services;

import org.abc.data.entity.Student;
import org.abc.data.entity.User;
import org.abc.exceptions.NotFoundException;
import org.springframework.stereotype.Service;


public interface UserService {

    User findUserByEmail(String email);

    void updateUser(User user) throws NotFoundException;

    void createUser(User user);

    User getUser(int userId) throws NotFoundException;
}
