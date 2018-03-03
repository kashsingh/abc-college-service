package org.abc.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.abc.data.dto.ScoringSubjectReport;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;



@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@Entity
@Data
@NamedNativeQuery(name = "Subject.getScoringSubjectReport",
        query = "SELECT subject_id, subject_name, abc_portal.student.course_id as course_id, AVG(marks) as average_marks " +
        "FROM ((abc_portal.student " +
        "INNER JOIN abc_portal.marks ON abc_portal.student.id = abc_portal.marks.student_id) " +
        "INNER JOIN abc_portal.subject ON abc_portal.marks.subject_id = abc_portal.subject.id) " +
        "WHERE batch = :batch AND abc_portal.student.course_id = :courseId " +
        "GROUP BY subject_id "+
        "ORDER BY average_marks DESC",
        resultSetMapping = "Subject.getScoringSubjectReport")

@SqlResultSetMapping(name="Subject.getScoringSubjectReport",
        classes = {
                @ConstructorResult(
                        targetClass = ScoringSubjectReport.class,
                        columns = {
                                @ColumnResult(name="subject_id"),
                                @ColumnResult(name="subject_name"),
                                @ColumnResult(name="course_id"),
                                @ColumnResult(name="average_marks", type = Float.class)
                        }
                )}
)
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
    @Column(name = "subject_name", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
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
