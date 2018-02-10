package org.abc.services;

import org.abc.data.dto.MarksDetails;
import org.abc.data.entity.Course;
import org.abc.data.entity.Subject;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;

import javax.annotation.Nonnull;
import java.util.List;

public interface SubjectService {

    @Nonnull
    List<Subject> viewCourseSubjects(Course course) throws NotFoundException;

    void createSubject(Subject subject);

    void updateSubject(Subject subject) throws NotFoundException;

    void deleteSubject(Subject subject) throws NotFoundException;

    Subject getAllSubjects(int subjectId) throws NotFoundException;

    List<MarksDetails> getStudentEnrolledSubjectMarksForSemester(Integer studentId, Integer semester) throws BadRequestException;

    List<Subject> getStudentAllEnrolledSubjects(Integer studentId) throws BadRequestException;

}
