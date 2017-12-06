package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@Entity
@Data
public class Admin {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    @Nonnull
    @Column(name = "password", columnDefinition = "VARCHAR(64)", nullable = false)
    private String password;

    @Nonnull
    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    public Admin(
            @JsonProperty("id") Integer id,
            @JsonProperty("email") @Nonnull String email,
            @JsonProperty("password")@Nonnull String password,
            @JsonProperty("name") @Nonnull String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
