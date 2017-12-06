package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@Entity
@Data
public class Subject {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "course_id", columnDefinition = "INT", nullable = false)
    private Course course;

    @Nonnull
    @JsonProperty("subject_name")
    @Column(name = "subject_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String subjectName;

    public Subject() {
    }

    public Subject(
            @JsonProperty("id") Integer id,
            @JsonProperty("course") @Nonnull Course course,
            @JsonProperty("subject_name") @Nonnull String subjectName) {
        this.id = id;
        this.course = course;
        this.subjectName = subjectName;
    }
}
