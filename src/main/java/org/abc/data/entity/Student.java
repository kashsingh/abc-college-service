package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Data
public class Student {

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
    @JsonProperty("name")
    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Nonnull
    @Column(name = "password", columnDefinition = "VARCHAR(64)", nullable = false)
    private String password;

    @Nonnull
    @JsonProperty("course")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "course_id", columnDefinition = "INT", nullable = false)
    private Course course;

    @Nonnull
    @JsonProperty("batch")
    @Column(name = "batch", columnDefinition = "VARCHAR(255)", nullable = false)
    private String batch;

    @Nonnull
    @JsonProperty("phone")
    @Column(name = "phone", columnDefinition = "VARCHAR(11)", nullable = false)
    private String phone;

    @Nonnull
    @JsonProperty("gender")
    @Column(name = "gender", columnDefinition = "CHAR(1)", nullable = false)
    private char gender;

    public Student(Integer id, @Nonnull String email, @Nonnull String name, @Nonnull String password, @Nonnull Course course, @Nonnull String batch, @Nonnull String phone, @Nonnull char gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.course = course;
        this.batch = batch;
        this.phone = phone;
        this.gender = gender;
    }
}
