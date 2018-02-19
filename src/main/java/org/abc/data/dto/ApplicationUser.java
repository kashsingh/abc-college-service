package org.abc.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;

@Data
public class ApplicationUser {

    @Nonnull
    private long userId;

    @Nonnull
    private String username;

    @Nonnull
    private String password;

    @Nonnull
    private String firstname;

    @Nonnull
    private String lastname;

    @Nonnull
    private String email;

    @Nonnull
    private String token;

    @Nonnull
    private boolean isAdmin;

    public ApplicationUser() {
    }

    public ApplicationUser(
            @JsonProperty("userId")long userId,
            @JsonProperty("username")String username,
            @JsonProperty("password") String password,
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("email") String email,
            @JsonProperty("token") String token,
            @JsonProperty("isAdmin") boolean isAdmin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.token = token;
        this.isAdmin = isAdmin;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
