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
public class Marks {

    @Nullable
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @JsonProperty("studentId")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;

    @Nonnull
    @JsonProperty("subjectId")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "semester", columnDefinition = "TINYINT", nullable = false)
    private int semester;

    @Column(name = "marks", columnDefinition = "FLOAT")
    private float marks;

    public Marks() {
    }

    public Marks(
            @JsonProperty("id") @Nullable Integer id,
            @JsonProperty("studentId") @Nonnull Student student,
            @JsonProperty("subjectId") @Nonnull Subject subject,
            @JsonProperty("semester") int semester,
            @JsonProperty("marks") float marks) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.semester = semester;
        this.marks = marks;
    }
}
