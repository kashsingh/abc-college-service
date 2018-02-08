package org.abc.services;

import org.abc.data.entity.Course;
import org.abc.data.entity.Subject;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface SubjectService {

    @Nonnull
    List<Subject> viewCourseSubjects(Course course) throws NotFoundException;

    void createSubject(Subject subject);

    void updateSubject(Subject subject) throws NotFoundException;

    void deleteSubject(Subject subject) throws NotFoundException;

    Subject getAllSubjects(int subjectId) throws NotFoundException;

    List<Subject> getStudentEnrolledSubjectsForSemester(Integer studentId, Integer semester) throws BadRequestException;

    List<Subject> getStudentAllEnrolledSubjects(Integer studentId) throws BadRequestException;

}
