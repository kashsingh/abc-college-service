package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Data
public class Marks {

    @Nullable
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @JsonProperty("studentId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @Nonnull
    @JsonProperty("subjectId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Nonnull
    @JsonProperty("semester")
    @Column(name = "semester", columnDefinition = "TINYINT", nullable = false)
    private int semester;

    @Nullable
    @JsonProperty("marks")
    @Column(name = "marks", columnDefinition = "FLOAT")
    private float marks;

    public Marks(Integer id, @Nonnull Student student, @Nonnull Subject subject, @Nonnull int semester, float marks) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.semester = semester;
        this.marks = marks;
    }
}
