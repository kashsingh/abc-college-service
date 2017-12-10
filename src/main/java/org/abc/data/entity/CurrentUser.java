/*
package org.abc.data.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getAuthorities().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Collection<GrantedAuthority> getRole() {
        return user.getAuthorities();
    }
}
*/
