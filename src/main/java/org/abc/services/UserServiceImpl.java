package org.abc.services;

import org.abc.data.dto.EditDetails;
import org.abc.data.entity.security.User;
import org.abc.data.repository.UserRepository;
import org.abc.exceptions.NotFoundException;
import org.abc.security.JwtTokenUtil;
import org.abc.security.models.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public void setUserRepository(@Nonnull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUser(EditDetails editDetails) throws NotFoundException {

        User existingUser = userRepository.findByUsername(editDetails.getUsername());

        if (existingUser == null) {
            throw new NotFoundException("User not found!!");
        } else {
            existingUser.setPassword(editDetails.getPassword());
            existingUser.setFirstname(editDetails.getFirstname());
            existingUser.setLastname(editDetails.getLastname());
            existingUser.setEmail(editDetails.getEmail());
            userRepository.save(existingUser);
        }
    }

    @Override
    public JwtUser getLoggedUser(HttpServletRequest request) throws NotFoundException {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }


}
