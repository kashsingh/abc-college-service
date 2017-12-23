package org.abc.services;

import org.abc.data.dto.EditUserDetails;
import org.abc.data.dto.StudentUser;
import org.abc.data.dto.UpdateMarks;
import org.abc.data.entity.*;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.util.List;

public interface AdminService {

    void createStudentUser(StudentUser studentUser);

    void updateStudentDetails(int studentId, EditUserDetails editUserDetails) throws NotFoundException;

    @Nonnull
    StudentUser getStudent(int studentId) throws NotFoundException;

    void deleteStudent(int studentId) throws NotFoundException;

    void assignStudentMarks(int studentId, int semester, UpdateMarks newMarks) throws NotFoundException, BadRequestException;

    @Nonnull
    List<Pair> getTopperStudentForBatch(Course course, String batch) throws NotFoundException;

    @Nonnull
    List<Pair> getHighestAndLowestScoreSubjects(Course course) throws NotFoundException;

    @Nonnull
    List<List<Pair>> getClassResult(Course course, String batch, double threshold) throws NotFoundException;

}
