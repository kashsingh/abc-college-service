package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {

    @Nullable
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Nonnull
    @Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    @Nonnull
    @Column(name = "password", columnDefinition = "VARCHAR(64)", nullable = false)
    private String password;

    @Nonnull
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Nonnull
    @Column(name = "phone", columnDefinition = "VARCHAR(11)", nullable = false)
    private String phone;

    @Nonnull
    @Column(name = "gender", columnDefinition = "CHAR(1)", nullable = false)
    private char gender;

    private boolean enabled;

    public User() {
    }

    public User(
            @JsonProperty("id") @Nullable Integer id,
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("email") @Nonnull String email,
            @JsonProperty("password") @Nonnull String password,
            @JsonProperty("roles") @Nonnull Set<Role> roles,
            @JsonProperty("phone") @Nonnull String phone,
            @JsonProperty("gender") @Nonnull char gender, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phone = phone;
        this.gender = gender;
        this.enabled = enabled;
    }

    @Nonnull
    @JsonIgnore
    public String getPassword() {
        return password;
    }


}
