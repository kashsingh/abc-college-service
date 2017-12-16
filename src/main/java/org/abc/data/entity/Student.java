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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @JsonProperty("user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

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


    public Student() {
    }

    public Student(
            @JsonProperty("id") Integer id,
            @JsonProperty("user_id") @Nonnull User user,
            @JsonProperty("course") @Nonnull Course course,
            @JsonProperty("batch") @Nonnull String batch,
            @JsonProperty("current_semester") @Nonnull int currentSemester) {
        this.id = id;
        this.course = course;
        this.batch = batch;
        this.currentSemester = currentSemester;
        }

}
