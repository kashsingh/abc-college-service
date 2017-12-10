package org.abc.data.DTO;

import lombok.Data;
import org.abc.data.entity.Course;
import org.abc.data.entity.Role;
import org.abc.data.entity.User;

@Data
public class StudentUser {
    private User user;
    private Course course;
    private String batch;

    public StudentUser() {
    }

    public void setUser(User user) {
        user.setEnabled(true);
        user.setRole(Role.ROLE_USER);
        this.user = user;
    }
}
