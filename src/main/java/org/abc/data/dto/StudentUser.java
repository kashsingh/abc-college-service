package org.abc.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.abc.data.entity.Course;

@Data
public class StudentUser {

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
            @JsonProperty("username")String username,
            @JsonProperty("password") String password,
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("email") String email,
            @JsonProperty("course") Course course,
            @JsonProperty("batch") String batch) {
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
            @JsonProperty("username")String username,
            @JsonProperty("password") String password,
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("email") String email,
            @JsonProperty("course") Course course,
            @JsonProperty("batch") String batch,
            @JsonProperty("current_semester") int currentSemester) {
        this.username = username;
        this.password = password;
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
