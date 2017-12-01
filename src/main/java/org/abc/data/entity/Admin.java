package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Data
public class Admin {

    @Nullable
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @JsonProperty("email")
    @Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    @Nonnull
    @Column(name = "password", columnDefinition = "VARCHAR(64)", nullable = false)
    private String password;

    @Nonnull
    @JsonProperty("name")
    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    public Admin(Integer id, @Nonnull String email, @Nonnull String password, @Nonnull String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
