package org.abc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;

@Data
public class MarksDetails {

    @Nonnull
    private Integer studentId;

    @Nonnull
    private Integer subjectId;

    @Nonnull
    private int semester;

    @Nonnull
    private String subjectName;

    @Nonnull
    private float marks;

    public MarksDetails() {
    }

    public MarksDetails(
            @JsonProperty("student_id") @Nonnull Integer studentId,
            @JsonProperty("subject_id") @Nonnull Integer subjectId,
            @JsonProperty("semester") @Nonnull int semester,
            @JsonProperty("subject_name") @Nonnull String subjectName,
            @JsonProperty("marks") @Nonnull float marks) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.semester = semester;
        this.subjectName = subjectName;
        this.marks = marks;
    }

}




