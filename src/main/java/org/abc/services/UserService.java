package org.abc.services;

import org.abc.data.dto.EditDetails;
import org.abc.exceptions.NotFoundException;
import org.abc.security.models.JwtUser;

import javax.servlet.http.HttpServletRequest;


public interface UserService {

    void updateUser(EditDetails editDetails) throws NotFoundException;

    JwtUser getLoggedUser(HttpServletRequest request) throws NotFoundException;

}
