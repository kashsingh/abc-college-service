package org.abc.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.abc.data.entity.Course;

import javax.annotation.Nullable;

@Data
public class StudentUser {

    @Nullable
    private int studentId;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Course course;
    private String batch;
    private int currentSemester;

    public StudentUser() {
    }

    public StudentUser(
            @JsonProperty("student_id") int studentId,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("email") String email,
            @JsonProperty("course") Course course,
            @JsonProperty("batch") String batch) {
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.course = course;
        this.batch = batch;
        this.currentSemester = 0;
    }

    public StudentUser(
            int studentId,
            String username,
            String firstname,
            String lastname,
            String email,
            Course course,
            String batch,
            int currentSemester) {
        this.studentId = studentId;
        this.username = username;
        this.password = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.course = course;
        this.batch = batch;
        this.currentSemester = currentSemester;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
