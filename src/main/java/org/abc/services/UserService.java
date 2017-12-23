package org.abc.services;

import org.abc.data.dto.EditUserDetails;
import org.abc.exceptions.NotFoundException;
import org.abc.security.models.JwtUser;

import javax.servlet.http.HttpServletRequest;


public interface UserService {

    void updateUser(long userId, EditUserDetails editUserDetails) throws NotFoundException;

    JwtUser getLoggedUser(HttpServletRequest request) throws NotFoundException;

}
