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
    @JsonProperty("userId")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Admin() {
    }

    public Admin(
            @JsonProperty("id") Integer id,
            @JsonProperty("userId") @Nonnull User user) {
        this.id = id;
        this.user = user;
    }
}
