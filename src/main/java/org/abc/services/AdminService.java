package org.abc.services;

import org.abc.data.dto.EditDetails;
import org.abc.data.dto.StudentUser;
import org.abc.data.entity.*;
import org.abc.exceptions.NotFoundException;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.util.List;

public interface AdminService {

    void createStudentUser(StudentUser studentUser);

    @Nonnull
    StudentUser getStudent(int studentId) throws NotFoundException;

    void deleteStudent(int studentId) throws NotFoundException;

    void assignStudentMarks(int studentID, List<Marks> marks) throws NotFoundException;

    @Nonnull
    List<Pair> getTopperStudentForBatch(Course course, String batch) throws NotFoundException;

    @Nonnull
    List<Pair> getHighestAndLowestScoreSubjects(Course course) throws NotFoundException;

    @Nonnull
    List<List<Pair>> getClassResult(Course course, String batch, double threshold) throws NotFoundException;

}
