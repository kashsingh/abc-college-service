package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class Student {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    @Nonnull
    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Nonnull
    @Column(name = "password", columnDefinition = "VARCHAR(64)", nullable = false)
    private String password;

    @Nonnull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "course_id", columnDefinition = "INT", nullable = false)
    private Course course;

    @Nonnull
    @Column(name = "batch", columnDefinition = "VARCHAR(255)", nullable = false)
    private String batch;

    @Nonnull
    @Column(name = "current_semester", columnDefinition = "TINYINT", nullable = false)
    private int currentSemester;

    @Nonnull
    @Column(name = "phone", columnDefinition = "VARCHAR(11)", nullable = false)
    private String phone;

    @Nonnull
    @Column(name = "gender", columnDefinition = "CHAR(1)", nullable = false)
    private char gender;

    public Student() {
    }

    public Student(
            @JsonProperty("id") Integer id,
            @JsonProperty("email") @Nonnull String email,
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("password") @Nonnull String password,
            @JsonProperty("course") @Nonnull Course course,
            @JsonProperty("batch") @Nonnull String batch,
            @JsonProperty("current_semester") @Nonnull int currentSemester,
            @JsonProperty("phone") @Nonnull String phone,
            @JsonProperty("gender") @Nonnull char gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.course = course;
        this.batch = batch;
        this.currentSemester = currentSemester;
        this.phone = phone;
        this.gender = gender;
    }
}
