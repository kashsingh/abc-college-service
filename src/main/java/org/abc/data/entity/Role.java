package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/*public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_SUPER;

    @Override
    public String getAuthority() {
        return name();
    }
}*/

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @JsonProperty("role_name")
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)   //, cascade = CascadeType.MERGE
    private Set<User> users;

    public Role() {
    }

    public Role(Integer id, String roleName, Set<User> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }

}