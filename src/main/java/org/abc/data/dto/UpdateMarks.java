package org.abc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

@Data
public class UpdateMarks {

    @Nonnull
    private List<Map<String, String>> subjectMarks;

    public UpdateMarks() {
    }

    public UpdateMarks(@JsonProperty("subjects_marks") @Nonnull List<Map<String, String>> subjectMarks) {
        this.subjectMarks = subjectMarks;
    }

}
