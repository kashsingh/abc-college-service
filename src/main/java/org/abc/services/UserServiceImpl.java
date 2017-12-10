package org.abc.services;

import org.abc.data.entity.User;
import org.abc.data.repository.UserRepository;
import org.abc.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service
public class UserServiceImpl implements UserService {

    @Nonnull
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String username) {
        return userRepository.findUserByEmail(username);
    }

    @Override
    public void updateUser(User user) throws NotFoundException {
        if (userRepository.findUserByEmail(user.getEmail()) == null) {
            throw new NotFoundException("User not found");
        }
        userRepository.save(user);
    }
}
