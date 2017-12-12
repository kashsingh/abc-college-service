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

    public StudentUser(User user, Course course, String batch) {
        this.user = user;
        this.course = course;
        this.batch = batch;
    }
}
