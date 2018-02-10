package org.abc.services;

import org.abc.data.dto.EditUserDetails;
import org.abc.data.entity.security.User;
import org.abc.data.repository.UserRepository;
import org.abc.exceptions.NotFoundException;
import org.abc.security.JwtTokenUtil;
import org.abc.security.models.JwtUser;
import org.abc.utils.TimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.token.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Nonnull
    private UserRepository userRepository;

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    public void setUserRepository(@Nonnull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUser(long userId, EditUserDetails editUserDetails) throws NotFoundException {

        User existingUser = userRepository.findUserById(userId);

        if (existingUser == null) {
            throw new NotFoundException("User not found!!");
        }

        // Get a new password encoder and encode the new password.
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        editUserDetails.setPassword(passwordEncoder.encode(editUserDetails.getPassword()));

        // Update the existingUser details and save it.
        existingUser.setPassword(editUserDetails.getPassword());
        existingUser.setLastPasswordResetDate(timeProvider.now());
        existingUser.setFirstname(editUserDetails.getFirstname());
        existingUser.setLastname(editUserDetails.getLastname());
        existingUser.setEmail(editUserDetails.getEmail());
        userRepository.save(existingUser);

    }

    @Override
    public JwtUser getLoggedUser(HttpServletRequest request) throws NotFoundException {

        // Get token header from the request.
        String token = request.getHeader(tokenHeader).substring(7);
        // Get username from the token.
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }


}
