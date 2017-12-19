package org.abc.services;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.abc.data.dto.EditDetails;
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
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    public void updateUser(long userId, EditDetails editDetails) throws NotFoundException {

        User existingUser = userRepository.findUserById(userId);

        if (existingUser == null) {
            throw new NotFoundException("User not found!!");
        }
        editDetails.setPassword(new BCryptPasswordEncoder().encode(existingUser.getPassword()));
        existingUser.setPassword(editDetails.getPassword());
        existingUser.setLastPasswordResetDate(timeProvider.now());
        existingUser.setFirstname(editDetails.getFirstname());
        existingUser.setLastname(editDetails.getLastname());
        existingUser.setEmail(editDetails.getEmail());
        userRepository.save(existingUser);

    }

    @Override
    public JwtUser getLoggedUser(HttpServletRequest request) throws NotFoundException {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }


}
