package org.abc.services;

import org.abc.data.entity.Role;
import org.abc.data.entity.User;
import org.abc.data.repository.RoleRepository;
import org.abc.data.repository.UserRepository;
import org.abc.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Nonnull
    private UserRepository userRepository;

    @Nonnull
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRoleRepository(@Nonnull RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void updateUser(User user) throws NotFoundException {
        if (userRepository.findUserByEmail(user.getEmail()) == null) {
            throw new NotFoundException("User not found");
        }
        userRepository.save(user);
    }

    @Override
    public void createUser(User user) {
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(roleRepository.findRoleByRoleName("ROLE_ADMIN"));
//        adminRoles.add(roleRepository.findRoleByRoleName("ROLE_USER"));
//        user.setRoles(adminRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUser(int userId) throws NotFoundException {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException("User not found!");
        }
        return user;
    }
}
