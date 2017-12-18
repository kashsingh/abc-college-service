package org.abc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;

@Data
public class EditDetails {

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

    public EditDetails() {
    }

    public EditDetails(
            @JsonProperty("username")String username,
            @JsonProperty("password") String password,
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("email") String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
