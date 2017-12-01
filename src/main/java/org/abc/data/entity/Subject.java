package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Data
public class Subject {

    @Nullable
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @JsonProperty("course")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "course_id", columnDefinition = "INT", nullable = false)
    private Course course;

    @Nonnull
    @JsonProperty("subject_name")
    @Column(name = "subject_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String subjectName;

    public Subject(Integer id, @Nonnull Course course, @Nonnull String subjectName) {
        this.id = id;
        this.course = course;
        this.subjectName = subjectName;
    }
}
