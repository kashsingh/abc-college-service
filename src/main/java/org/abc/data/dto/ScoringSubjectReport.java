package org.abc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ScoringSubjectReport {

    private int subjectId;
    private String subjectName;
    private int courseId;
    private float averageMarks;

    public ScoringSubjectReport() {
    }

    public ScoringSubjectReport(
            @JsonProperty("subjectId") int subjectId,
            @JsonProperty("subjectName") String subjectName,
            @JsonProperty("courseId") int courseId,
            @JsonProperty("averageMarks") float averageMarks) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseId = courseId;
        this.averageMarks = averageMarks;
    }
}
