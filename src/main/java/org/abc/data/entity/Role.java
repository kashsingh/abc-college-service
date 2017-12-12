package org.abc.data.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/*public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_SUPER;

    @Override
    public String getAuthority() {
        return name();
    }
}*/

@Entity
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    public Role() {
    }

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}