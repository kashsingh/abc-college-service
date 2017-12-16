package org.abc.data.DTO;

import lombok.Data;

@Data
public class UserCred {
    private String username;
    private String password;

    public UserCred() {
    }

    public UserCred(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
